package com.diplomski.common.turn;

import com.diplomski.common.board.BoardState;

public interface ITurnProviderFactory {
	public ITurnProvider getTurnProvider(String initiatorId, BoardState boardState);
}
