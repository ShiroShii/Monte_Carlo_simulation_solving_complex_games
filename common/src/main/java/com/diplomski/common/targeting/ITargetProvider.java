package com.diplomski.common.targeting;

import com.diplomski.common.board.BoardState;

public interface ITargetProvider {
	public int getTargetCharacterIndex(int initiatingCharacterIndex, BoardState boardState);
}
