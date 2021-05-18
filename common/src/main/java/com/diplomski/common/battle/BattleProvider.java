package com.diplomski.common.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.board.IBoardStateProvider;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.round.IRoundProvider;
import com.diplomski.common.round.Round;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BattleProvider implements IBattleProvider {

	private final IBoardStateProvider boardStateProvider;
	private final IRoundProvider roundProvider;

	@Override
	public Battle getBattle(List<CharacterState> initialCharacterStates, int roundCountLimit) {
		BoardState initialBoardState = boardStateProvider.getInitialBoardState(initialCharacterStates);
		BoardState roundInitialBoardState = initialBoardState.toBuilder().build();
		List<Round> rounds = new ArrayList<>();
		do {
			Round round = roundProvider.getRound(roundInitialBoardState);
			rounds.add(round);

			roundInitialBoardState = round.getFinalBoardState().toBuilder().build();

			roundInitialBoardState.resetSpeed();
		} while (!roundInitialBoardState.isBattleComplete() || roundCountLimit == rounds.size());

		BoardState finalBoardState = rounds.isEmpty() ? initialBoardState
				: rounds.get(rounds.size() - 1).getFinalBoardState();

		Optional<Party> winningParty = Optional.empty();

		if (finalBoardState.getPartyHp(Party.ENEMY) == 0) {
			winningParty = Optional.of(Party.PLAYER);
		} else if (finalBoardState.getPartyHp(Party.PLAYER) == 0) {
			winningParty = Optional.of(Party.ENEMY);
		}

		return Battle.builder().initialBoardState(initialBoardState).rounds(rounds).finalBoardState(finalBoardState)
				.winningParty(winningParty).isBattleComplete(winningParty.isPresent()).build();
	}
}
