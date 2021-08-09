package com.diplomski.common.battle;

import java.util.List;

import com.diplomski.common.board.IBoard;
import com.diplomski.common.character.ICharacterState;

public interface IBattleProvider {
	public Battle getBattle(List<ICharacterState> initialCharacterStates, int roundCountLimit, IBoard board);
}
