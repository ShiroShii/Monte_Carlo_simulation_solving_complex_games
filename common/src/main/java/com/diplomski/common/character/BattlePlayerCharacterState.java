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
public class BattlePlayerCharacterState extends CharacterState implements IBattleCharacterState{
	private int usedWalkingSpeed;

	private ITurnProvider turnProvider;
	
    public static BattlePlayerCharacterStateBuilder<?, ?> toBuilder(CharacterState characterState) {
        return new BattlePlayerCharacterStateBuilderImpl().$fillValuesFromParent(characterState);
    }
}
