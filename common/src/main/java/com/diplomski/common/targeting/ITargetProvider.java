package com.diplomski.common.targeting;

import java.util.Optional;
import java.util.UUID;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.Party;

public interface ITargetProvider {
	public Optional<UUID> getTargetId(UUID initiatorId, Party targetParty, BoardState boardState);
}
