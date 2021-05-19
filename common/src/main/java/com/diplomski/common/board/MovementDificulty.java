package com.diplomski.common.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MovementDificulty {
	REGULAR_MOVEMENT(5),
	DIFICULT_MOVEMENT(10);

	@Getter
	private final Integer movementCost;
}
