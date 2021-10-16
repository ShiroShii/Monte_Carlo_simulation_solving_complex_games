package com.diplomski.backend.translator;

import java.util.List;

import com.diplomski.backend.contract.BattleResponse;
import com.diplomski.backend.contract.MonsterStateContract;
import com.diplomski.backend.contract.NodeTileContract;
import com.diplomski.backend.contract.PlayerCharacterStateContract;
import com.diplomski.backend.dal.BattleDbModel;
import com.diplomski.backend.dal.MonsterStateDbModel;
import com.diplomski.backend.dal.NodeTileDbModel;
import com.diplomski.backend.dal.PlayerCharacterStateDbModel;

public class BattleTranslator {
	public static PlayerCharacterStateContract translate(PlayerCharacterStateDbModel input) {
		return PlayerCharacterStateContract.builder()
				.playerCharacterId(input.getPlayerCharacter().getId())
				.currentHp(input.getCurrentHp())
				.targetingStyle(input.getTargetingStyle())
				.playStyle(input.getPlayStyle())
				.build();
	}

	public static MonsterStateContract translate(MonsterStateDbModel input) {
		return MonsterStateContract.builder()
				.monster(input.getMonster())
				.currentHp(input.getCurrentHp())
				.targetingStyle(input.getTargetingStyle())
				.playStyle(input.getPlayStyle())
				.build();
	}

	public static NodeTileContract translate(NodeTileDbModel input) {
		return NodeTileContract.builder()
				.id(input.getId())
				.x(input.getX())
				.y(input.getY())
				.reachableTiles(input.getReachableNodes().stream().map(x -> x.getId()).toList())
				.terrainFeature(input.getTerrainFeature())
				.playerCharacterStates(input.getCharacterStates().stream().map(x -> translate(x)).toList())
				.monsterStates(input.getMonsterStates().stream().map(x -> translate(x)).toList())
				.build();
	}

	public static BattleResponse translate(BattleDbModel input) {
		return BattleResponse.builder()
				.id(input.getId()).name(input.getName())
				.tiles(input.getNodeTiles().stream().map(x -> translate(x)).toList())
				.build();
	}

	public static List<BattleResponse> translate(List<BattleDbModel> input) {
		return input.stream().map(x -> translate(x)).toList();
	}
}
