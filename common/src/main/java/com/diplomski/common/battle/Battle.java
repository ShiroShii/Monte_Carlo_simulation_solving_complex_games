package com.diplomski.common.battle;

import java.util.List;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.round.Round;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Battle {
	private List<Round> rounds;
	private BoardState initialBoardState;
	private BoardState finalBoardState;
}
