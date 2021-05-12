package com.diplomski.common.round;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.turn.Turn;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundProvider implements IRoundProvider {
	@Override
	public Round getRound(BoardState initialBoardState) {
		BoardState currentBoardState = initialBoardState;
		List<Turn> turns = new ArrayList<>();

		for (String characterId : initialBoardState.getCharacterStates().keySet()) {
			// End round if all members of a party have HP = 0
			boolean battleComplete = getPartyHp(currentBoardState.getCharacterStates(), Party.PLAYER) == 0
					|| getPartyHp(currentBoardState.getCharacterStates(), Party.ENEMY) == 0;

			if (battleComplete) {
				break;
			}

			BattleCharacterState characterState = currentBoardState.getCharacterStates().get(characterId);

			if (characterState.getCurrentHp() == 0) {
				continue;
			}

			Turn turn = characterState.getTurnProvider().getTurn(currentBoardState);

			turns.add(turn);
			currentBoardState = turn.getFinalBoardState();
		}

		return Round.builder().initialBoardState(initialBoardState).finalBoardState(currentBoardState).turns(turns)
				.build();
	}

	private int getPartyHp(LinkedHashMap<String, BattleCharacterState> characterStates, Party party) {
		return characterStates.values().stream().filter(x -> x.getParty().equals(party)).mapToInt(x -> x.getCurrentHp())
				.sum();
	}
}
