package com.diplomski.common.round;

import java.util.List;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.turn.Turn;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Round {
	private List<Turn> turns;
	private BoardState initialBoardState;
	private BoardState finalBoardState;
}
