package com.diplomski.common.board;

import java.util.List;

import com.diplomski.common.character.ICharacterState;

public interface IBoardStateProvider {
	public BoardState getInitialBoardState(List<ICharacterState> characterStates, IBoard board);
}
