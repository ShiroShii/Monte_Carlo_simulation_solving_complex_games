package com.diplomski.common.turn;

import com.diplomski.common.board.BoardState;

public interface ITurnProvider {
	public Turn getTurn(int initiatingCharacterIndex, BoardState initialBoardState);
}
