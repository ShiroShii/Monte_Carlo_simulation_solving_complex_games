package com.diplomski.backend.translator;

import java.util.List;
import java.util.stream.Collectors;

import com.diplomski.backend.contract.BattleResponse;
import com.diplomski.backend.contract.MonsterStateResponse;
import com.diplomski.backend.contract.NodeTileResponse;
import com.diplomski.backend.contract.PlayerCharacterStateResponse;
import com.diplomski.backend.dal.BattleDbModel;
import com.diplomski.backend.dal.MonsterStateDbModel;
import com.diplomski.backend.dal.NodeTileDbModel;
import com.diplomski.backend.dal.PlayerCharacterStateDbModel;

public class BattleTranslator {
	public static PlayerCharacterStateResponse translate(PlayerCharacterStateDbModel input) {
		return PlayerCharacterStateResponse.builder()
				.id(input.getId())
				.playerCharacterId(input.getPlayerCharacter().getId())
				.currentHp(input.getCurrentHp())
				.targetingStyle(input.getTargetingStyle())
				.playStyle(input.getPlayStyle())
				.tileId(input.getNodeTile().getId())
				.build();
	}

	public static MonsterStateResponse translate(MonsterStateDbModel input) {
		return MonsterStateResponse.builder()
				.id(input.getId())
				.monster(input.getMonster())
				.targetingStyle(input.getTargetingStyle())
				.playStyle(input.getPlayStyle())
				.currentHp(input.getCurrentHp())
				.tileId(input.getNodeTile().getId())
				.build();
	}

	public static NodeTileResponse translate(NodeTileDbModel input) {
		return NodeTileResponse.builder()
				.id(input.getId())
				.terrainFeature(input.getTerrainFeature())
				.reachableTiles(input.getReachableNodes().stream().map(x -> x.getId()).collect(Collectors.toList()))
				.build();
	}

	public static BattleResponse translate(BattleDbModel input) {
		return BattleResponse.builder()
				.id(input.getId()).name(input.getName())
				.playerCharacterStates(input.getNodeTiles().stream()
						.flatMap(x -> x.getCharacterStates().stream())
						.map(x -> translate(x)).toList())
				.tiles(input.getNodeTiles().stream().map(x -> translate(x)).toList())
				.monsterStates(input.getNodeTiles().stream().flatMap(x -> x.getMonsterStates().stream())
						.map(x -> translate(x)).toList())
				.build();
	}

	public static List<BattleResponse> translate(List<BattleDbModel> input) {
		return input.stream().map(x -> translate(x)).toList();
	}
}
