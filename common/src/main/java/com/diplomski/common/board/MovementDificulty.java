package com.diplomski.common.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MovementDificulty {
	REGULAR(5), DIFFICULT(10);

	@Getter
	private final int movementCost;
}
