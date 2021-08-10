package com.diplomski.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.diplomski.backend.contract.BattleCreateRequest;
import com.diplomski.backend.contract.MonsterStateCreateRequest;
import com.diplomski.backend.contract.PlayerCharacterStateCreateRequest;
import com.diplomski.backend.dal.BattleDbModel;
import com.diplomski.backend.dal.MonsterStateDbModel;
import com.diplomski.backend.dal.NodeBoardDbModel;
import com.diplomski.backend.dal.NodeTileDbModel;
import com.diplomski.backend.dal.PlayerCharacterDbModel;
import com.diplomski.backend.dal.PlayerCharacterStateDbModel;
import com.diplomski.backend.repository.BattleRepository;
import com.diplomski.backend.repository.NodeBoardRepository;
import com.diplomski.backend.repository.PlayerCharacterRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BattleService {
	private BattleRepository battleRepository;
	private NodeBoardRepository boardRepository;
	private PlayerCharacterRepository playerCharacterRepository;

	public BattleDbModel save(BattleCreateRequest request) throws Exception {
		Optional<NodeBoardDbModel> board = boardRepository.findById(request.getBoardId());
		if (board.isEmpty()) {
			throw new Exception("Board not found");
		}

		BattleDbModel battle = BattleDbModel.builder().name(request.getName()).nodeBoard(board.get()).build();

		List<PlayerCharacterStateDbModel> playerCharacterStates = new ArrayList<>();
		for (PlayerCharacterStateCreateRequest characterRequest : request.getPlayerCharacterStates()) {
			Optional<PlayerCharacterDbModel> playerCharacter = playerCharacterRepository
					.findById(characterRequest.getPlayerCharacterId());
			if (playerCharacter.isEmpty()) {
				throw new Exception("PlayerCharacter not found");
			}

			Optional<NodeTileDbModel> tile = board.get().getNodeTiles().stream()
					.filter(x -> x.getId().equals(characterRequest.getTileId())).findFirst();

			if (tile.isEmpty()) {
				throw new Exception("Tile for PlayerCharacter was not found");
			}

			PlayerCharacterStateDbModel characterState = PlayerCharacterStateDbModel.builder().battle(battle)
					.party(characterRequest.getParty()).currentHp(characterRequest.getCurrentHp()).nodeTile(tile.get())
					.targetingStyle(characterRequest.getTargetingStyle()).playStyle(characterRequest.getPlayStyle())
					.playerCharacter(playerCharacter.get()).build();
			playerCharacterStates.add(characterState);
		}
		battle.setPlayerCharacterStates(playerCharacterStates);

		List<MonsterStateDbModel> monsterStates = new ArrayList<>();
		for (MonsterStateCreateRequest characterRequest : request.getMonsterStates()) {
			Optional<NodeTileDbModel> tile = board.get().getNodeTiles().stream()
					.filter(x -> x.getId().equals(characterRequest.getTileId())).findFirst();

			if (tile.isEmpty()) {
				throw new Exception("Tile for Monster was not found");
			}

			MonsterStateDbModel characterState = MonsterStateDbModel.builder().battle(battle)
					.party(characterRequest.getParty()).currentHp(characterRequest.getCurrentHp()).nodeTile(tile.get())
					.targetingStyle(characterRequest.getTargetingStyle()).playStyle(characterRequest.getPlayStyle())
					.monster(characterRequest.getMonster()).build();
			monsterStates.add(characterState);
		}
		battle.setMonsterStates(monsterStates);

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
