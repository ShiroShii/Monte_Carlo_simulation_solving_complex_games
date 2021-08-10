package com.diplomski.common.round;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.turn.Turn;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundProvider implements IRoundProvider {
	@Override
	public Round getRound(BoardState initialBoardState) {
		BoardState currentBoardState = initialBoardState;
		List<Turn> turns = new ArrayList<>();

		for (UUID characterId : initialBoardState.getCharacterStates().keySet()) {
			if (currentBoardState.isBattleComplete()) {
				break;
			}

			IBattleCharacterState characterState = currentBoardState.getCharacterStates().get(characterId);

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
