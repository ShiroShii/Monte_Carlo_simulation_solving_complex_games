package com.diplomski.common.targeting;

import java.util.Optional;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.Party;

public class RoundRobinTargetProvider implements ITargetProvider {

	@Override
	public Optional<String> getTargetId(String initiatorId, Party targetParty, BoardState boardState) {
		return boardState.getCharacterStates().values().stream()
				.filter(x -> x.getParty().equals(targetParty) && x.getCurrentHp() > 0).map(x -> x.getId()).findFirst();
	}
}
