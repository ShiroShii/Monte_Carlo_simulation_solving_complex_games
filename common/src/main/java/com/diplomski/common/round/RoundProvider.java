package com.diplomski.common.round;

import java.util.ArrayList;
import java.util.List;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.turn.Turn;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundProvider implements IRoundProvider {
	@Override
	public Round getRound(BoardState initialBoardState) {
		BoardState currentBoardState = initialBoardState; // TODO: reset remaining speed
		List<Turn> turns = new ArrayList<>();

		for (String characterId : initialBoardState.getCharacterStates().keySet()) {
			BattleCharacterState characterState = currentBoardState.getCharacterStates().get(characterId);
			// TODO: reset remaining speed
			// TODO: end turn if all members of the party have HP = 0
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
}
