package com.diplomski.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.diplomski.backend.contract.BattleCreateRequest;
import com.diplomski.backend.contract.MonsterStateCreateRequest;
import com.diplomski.backend.contract.NodeTileCreateRequest;
import com.diplomski.backend.contract.PlayerCharacterStateCreateRequest;
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

		List<NodeTileDbModel> tiles = new ArrayList<>();
		for (NodeTileCreateRequest tileRequest : request.getTiles()) {
			NodeTileDbModel tile = NodeTileDbModel.builder()
					.battle(battle)
					.terrainFeature(tileRequest.getTerrainFeature())
					.x(tileRequest.getX())
					.y(tileRequest.getY())
					.build();

			List<PlayerCharacterStateDbModel> playerCharacterStates = new ArrayList<>();
			for (PlayerCharacterStateCreateRequest playerRequest : tileRequest.getPlayerCharacterStates()) {
				Optional<PlayerCharacterDbModel> playerCharacter = playerCharacterRepository
						.findById(playerRequest.getPlayerCharacterId());

				if (playerCharacter.isEmpty()) {
					throw new Exception("PlayerCharacter not found");
				}

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
			for (MonsterStateCreateRequest monsterRequest : tileRequest.getMonsterStates()) {
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

			tiles.add(tile);
		}

		// Reach
		for (int i = 0; i < tiles.size(); i++) {
			NodeTileDbModel node = tiles.get(i);
			List<NodeTileDbModel> reachableNodes = new ArrayList<>();
			
			for (int reachableIndex : request.getTiles().get(i).getReachableTiles()) {
				reachableNodes.add(tiles.get(reachableIndex));
			}
			
			node.setReachableNodes(reachableNodes);
		}
		
		// Cover

		battle.setNodeTiles(tiles);

		return battleRepository.save(battle);
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
