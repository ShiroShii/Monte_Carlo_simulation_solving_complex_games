package com.diplomski.backend.translator;

import java.util.List;

import com.diplomski.backend.contract.BattleResponse;
import com.diplomski.backend.contract.MonsterStateResponse;
import com.diplomski.backend.contract.PlayerCharacterStateResponse;
import com.diplomski.backend.dal.BattleDbModel;
import com.diplomski.backend.dal.MonsterStateDbModel;
import com.diplomski.backend.dal.PlayerCharacterStateDbModel;

public class BattleTranslator {
	public static PlayerCharacterStateResponse translate(PlayerCharacterStateDbModel input) {
		return PlayerCharacterStateResponse.builder().id(input.getId())
				.playerCharacterId(input.getPlayerCharacter().getId()).currentHp(input.getCurrentHp())
				.targetingStyle(input.getTargetingStyle()).playStyle(input.getPlayStyle())
				.tileId(input.getNodeTile().getId()).build();
	}

	public static MonsterStateResponse translate(MonsterStateDbModel input) {
		return MonsterStateResponse.builder().id(input.getId()).monster(input.getMonster())
				.targetingStyle(input.getTargetingStyle()).playStyle(input.getPlayStyle())
				.currentHp(input.getCurrentHp()).tileId(input.getNodeTile().getId()).build();
	}

	public static BattleResponse translate(BattleDbModel input) {
		return BattleResponse.builder().id(input.getId()).name(input.getName())
				//.playerCharacterStates(input.getPlayerCharacterStates().stream().map(x -> translate(x)).toList())
				// .tiles
				//.monsterStates(input.getMonsterStates().stream().map(x -> translate(x)).toList())
				.build();
	}

	public static List<BattleResponse> translate(List<BattleDbModel> input) {
		return input.stream().map(x -> translate(x)).toList();
	}
}
