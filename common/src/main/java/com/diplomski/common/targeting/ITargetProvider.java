package com.diplomski.common.targeting;

import java.util.Optional;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.Party;

public interface ITargetProvider {
	public Optional<String> getTargetId(String initiatorId, Party targetParty, BoardState boardState);
}
