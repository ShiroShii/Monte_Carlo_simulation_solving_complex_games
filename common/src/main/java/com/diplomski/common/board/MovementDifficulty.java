package com.diplomski.common.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MovementDifficulty {
	REGULAR_MOVEMENT(5),
	DIFFICULT_MOVEMENT(10);

	@Getter
	private final Integer movementCost;
}
