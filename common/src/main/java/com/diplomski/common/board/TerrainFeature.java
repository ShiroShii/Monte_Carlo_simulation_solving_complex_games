package com.diplomski.common.board;

import static com.diplomski.common.board.MovementDifficulty.DIFFICULT_MOVEMENT;
import static com.diplomski.common.board.MovementDifficulty.REGULAR_MOVEMENT;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TerrainFeature {
	LOW_GRASS(REGULAR_MOVEMENT),
	MUD(DIFFICULT_MOVEMENT);

	@Getter
	private MovementDifficulty movementDifficulty;
}
