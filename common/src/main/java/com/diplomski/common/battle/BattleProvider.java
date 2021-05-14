package com.diplomski.common.battle;

import java.util.ArrayList;
import java.util.List;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.board.IBoardStateProvider;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.round.IRoundProvider;
import com.diplomski.common.round.Round;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BattleProvider implements IBattleProvider {

	private final IBoardStateProvider boardStateProvider;
	private final IRoundProvider roundProvider;

	@Override
	public Battle getBattle(List<CharacterState> initialCharacterStates) {
		BoardState initialBoardState = boardStateProvider.getInitialBoardState(initialCharacterStates);
		BoardState roundInitialBoardState = initialBoardState.toBuilder().build();
		List<Round> rounds = new ArrayList<>();
		do {
			Round round = roundProvider.getRound(roundInitialBoardState);
			rounds.add(round);

			roundInitialBoardState = round.getFinalBoardState().toBuilder().build();

			roundInitialBoardState.resetSpeed();
		} while (!roundInitialBoardState.isBattleComplete());

		return Battle.builder().initialBoardState(initialBoardState).rounds(rounds)
				.finalBoardState(
						rounds.isEmpty() ? initialBoardState : rounds.get(rounds.size() - 1).getFinalBoardState())
				.build();
	}
}
