package com.diplomski.common.round;

import java.util.ArrayList;
import java.util.List;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.turn.ITurnProvider;
import com.diplomski.common.turn.Turn;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundProvider implements IRoundProvider {
	private final ITurnProvider turnProvider;

	@Override
	public Round getRound(BoardState initialBoardState) {
		BoardState currentBoardState = initialBoardState; //TODO: reset remaining speed
		List<Turn> turns = new ArrayList<>();

		for (int i = 0; i < initialBoardState.getCharacterStates().size(); i++) {
			//TODO: reset remaining speed
			if (currentBoardState.getCharacterStates().get(i).getCurrentHp() == 0) {
				continue;
			}

			Turn turn = turnProvider.getTurn(i, currentBoardState);

			turns.add(turn);
			currentBoardState = turn.getFinalBoardState();
		}

		return Round.builder().initialBoardState(initialBoardState).finalBoardState(currentBoardState).turns(turns).build();
	}
}
