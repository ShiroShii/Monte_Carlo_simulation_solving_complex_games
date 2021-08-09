package com.diplomski.common.board;
import static com.diplomski.common.board.Concealment.NO_CONCEALMENT;
import static com.diplomski.common.board.Concealment.PARTIAL_CONCEALMENT;
import static com.diplomski.common.board.Concealment.TOTAL_CONCEALMENT;
import static com.diplomski.common.board.Cover.FULL_COVER;
import static com.diplomski.common.board.Cover.NO_COVER;
import static com.diplomski.common.board.MovementDificulty.DIFICULT_MOVEMENT;
import static com.diplomski.common.board.MovementDificulty.REGULAR_MOVEMENT;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TerrainFeature {
	LOW_GRASS(REGULAR_MOVEMENT, NO_CONCEALMENT, NO_COVER),
	LOW_SHRUB(REGULAR_MOVEMENT, PARTIAL_CONCEALMENT, NO_COVER),
	TREE(REGULAR_MOVEMENT, TOTAL_CONCEALMENT, FULL_COVER),
	MUD(DIFICULT_MOVEMENT, NO_CONCEALMENT, NO_COVER);

	@Getter
	private MovementDificulty movementDificulty;
	@Getter
	private Concealment concealment;
	@Getter
	private Cover cover;
}
