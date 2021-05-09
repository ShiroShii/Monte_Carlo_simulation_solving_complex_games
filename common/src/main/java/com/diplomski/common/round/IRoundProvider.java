package com.diplomski.common.round;

import com.diplomski.common.board.BoardState;

public interface IRoundProvider {
	public Round getRound(BoardState initialBoardState);
}
