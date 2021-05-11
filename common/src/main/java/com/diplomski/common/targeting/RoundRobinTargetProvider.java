package com.diplomski.common.targeting;

import java.util.Optional;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.character.Party;

public class RoundRobinTargetProvider implements ITargetProvider {

	@Override
	public Optional<Integer> getTargetCharacterIndex(int initiatorIndex, Party targetParty, BoardState boardState) {
		for(int i=0; i<boardState.getCharacterStates().size(); i++) {
			CharacterState targetCharacter = boardState.getCharacterStates().get(i);
			
			if(targetCharacter.getParty().equals(targetParty) && targetCharacter.getCurrentHp()>0) {
				return Optional.of(i);
			}
		}
		
		return Optional.empty();
	}
}
