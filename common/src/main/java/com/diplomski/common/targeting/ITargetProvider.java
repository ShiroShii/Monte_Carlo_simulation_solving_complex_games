package com.diplomski.common.targeting;

import java.util.Optional;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.Party;

public interface ITargetProvider {
	public Optional<Integer> getTargetCharacterIndex(int initiatorIndex, Party targetParty, BoardState boardState);
}
