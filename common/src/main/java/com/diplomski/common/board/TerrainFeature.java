package com.diplomski.common.board;

import static com.diplomski.common.board.MovementDificulty.DIFICULT_MOVEMENT;
import static com.diplomski.common.board.MovementDificulty.REGULAR_MOVEMENT;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TerrainFeature {
	LOW_GRASS(REGULAR_MOVEMENT),
	MUD(DIFICULT_MOVEMENT);

	@Getter
	private MovementDificulty movementDificulty;
}
