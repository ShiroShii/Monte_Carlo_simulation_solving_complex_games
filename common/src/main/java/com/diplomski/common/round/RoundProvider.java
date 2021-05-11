package com.diplomski.common.round;

import java.util.ArrayList;
import java.util.List;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.turn.Turn;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundProvider implements IRoundProvider {
	@Override
	public Round getRound(BoardState initialBoardState) {
		BoardState currentBoardState = initialBoardState; //TODO: reset remaining speed
		List<Turn> turns = new ArrayList<>();

		//TODO: get rid of int
		for (int i = 0; i < initialBoardState.getCharacterStates().size(); i++) {
			CharacterState characterState = currentBoardState.getCharacterStates().get(i);
			//TODO: reset remaining speed
			if (characterState.getCurrentHp() == 0) {
				continue;
			}

			Turn turn = characterState.getTurnProvider().getTurn(i, currentBoardState);

			turns.add(turn);
			currentBoardState = turn.getFinalBoardState();
		}

		return Round.builder().initialBoardState(initialBoardState).finalBoardState(currentBoardState).turns(turns).build();
	}
}
