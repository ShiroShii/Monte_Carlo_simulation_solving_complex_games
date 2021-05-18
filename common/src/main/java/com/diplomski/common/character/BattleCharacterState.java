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
public class BattleCharacterState extends CharacterState {
	private int usedWalkingSpeed;

	private ITurnProvider turnProvider;
	
    public static BattleCharacterStateBuilder<?, ?> toBuilder(CharacterState characterState) {
        return new BattleCharacterStateBuilderImpl().$fillValuesFromParent(characterState);
    }
}
