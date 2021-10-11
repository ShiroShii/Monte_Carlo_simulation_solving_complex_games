package com.diplomski.common.character;

import com.diplomski.common.turn.ITurnProvider;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlayerBattleCharacterState extends PlayerCharacterState implements IBattleCharacterState {
	
	private int usedSpeed;
	
	private int usedFlyingSpeed;

	private ITurnProvider turnProvider;

	public static PlayerBattleCharacterState getBattleState(
			PlayerCharacterState characterState,
			ITurnProvider turnProvider) {
		return PlayerBattleCharacterState.toBuilder(characterState).turnProvider(turnProvider).usedSpeed(0)
				.build();
	}

	public static PlayerBattleCharacterStateBuilder<?, ?> toBuilder(PlayerCharacterState characterState) {
		return new PlayerBattleCharacterStateBuilderImpl().$fillValuesFromParent(characterState);
	}
}
