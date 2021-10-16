package com.diplomski.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.diplomski.backend.contract.BattleCreateRequest;
import com.diplomski.backend.contract.MonsterStateContract;
import com.diplomski.backend.contract.NodeTileContract;
import com.diplomski.backend.contract.PlayerCharacterStateContract;
import com.diplomski.backend.dal.BattleDbModel;
import com.diplomski.backend.dal.MonsterStateDbModel;
import com.diplomski.backend.dal.NodeTileDbModel;
import com.diplomski.backend.dal.PlayerCharacterDbModel;
import com.diplomski.backend.dal.PlayerCharacterStateDbModel;
import com.diplomski.backend.repository.BattleRepository;
import com.diplomski.backend.repository.PlayerCharacterRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BattleService {
	private BattleRepository battleRepository;
	private PlayerCharacterRepository playerCharacterRepository;

	public BattleDbModel save(BattleCreateRequest request) throws Exception {
		BattleDbModel battle = BattleDbModel.builder().name(request.getName()).build();

		Map<UUID, NodeTileDbModel> tiles = request.getTiles()
				.stream()
				.collect(Collectors.toMap(x -> x.getId(), x -> getNodeTileDbModel(x, battle)));

		request.getTiles()
				.stream()
				.forEach(tileRequest -> {
					NodeTileDbModel tile = tiles.get(tileRequest.getId());
					List<NodeTileDbModel> reachableTiles = tiles.entrySet().stream()
							.filter(x -> tileRequest.getReachableTiles().contains(x.getKey()))
							.map(x -> x.getValue())
							.toList();
					tile.setReachableNodes(reachableTiles);
				});

		battle.setNodeTiles(new ArrayList<NodeTileDbModel>(tiles.values()));

		return battleRepository.save(battle);
	}

	private NodeTileDbModel getNodeTileDbModel(NodeTileContract tileRequest, BattleDbModel battle) {
		NodeTileDbModel tile = NodeTileDbModel.builder()
				.battle(battle)
				.terrainFeature(tileRequest.getTerrainFeature())
				.x(tileRequest.getX())
				.y(tileRequest.getY())
				.build();

		List<PlayerCharacterStateDbModel> playerCharacterStates = new ArrayList<>();
		for (PlayerCharacterStateContract playerRequest : tileRequest.getPlayerCharacterStates()) {
			Optional<PlayerCharacterDbModel> playerCharacter = playerCharacterRepository
					.findById(playerRequest.getPlayerCharacterId());

			PlayerCharacterStateDbModel characterState = PlayerCharacterStateDbModel.builder()
					.currentHp(playerRequest.getCurrentHp())
					.nodeTile(tile)
					.targetingStyle(playerRequest.getTargetingStyle())
					.playStyle(playerRequest.getPlayStyle())
					.playerCharacter(playerCharacter.get())
					.build();

			playerCharacterStates.add(characterState);
		}
		tile.setCharacterStates(playerCharacterStates);

		List<MonsterStateDbModel> monsterStates = new ArrayList<>();
		for (MonsterStateContract monsterRequest : tileRequest.getMonsterStates()) {
			MonsterStateDbModel monsterState = MonsterStateDbModel.builder()
					.currentHp(monsterRequest.getCurrentHp())
					.nodeTile(tile)
					.targetingStyle(monsterRequest.getTargetingStyle())
					.playStyle(monsterRequest.getPlayStyle())
					.monster(monsterRequest.getMonster())
					.build();

			monsterStates.add(monsterState);
		}
		tile.setMonsterStates(monsterStates);

		return tile;
	}

	public Optional<BattleDbModel> get(UUID id) {
		return battleRepository.findById(id);
	}

	public List<BattleDbModel> getAll() {
		return battleRepository.findAll();
	}

	public void delete(UUID id) {
		battleRepository.deleteById(id);
	}
}
