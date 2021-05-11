package com.diplomski.common.character;

import com.diplomski.common.turn.ITurnProvider;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CharacterState extends InitialCharacterState {
	private int usedWalkingSpeed;

	private ITurnProvider turnProvider;
}
