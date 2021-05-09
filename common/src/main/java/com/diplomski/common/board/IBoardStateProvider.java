package com.diplomski.common.board;

import java.util.List;

import com.diplomski.common.character.CharacterState;

public interface IBoardStateProvider {
	public BoardState getInitialBoardState(List<CharacterState> characterStates);
}
