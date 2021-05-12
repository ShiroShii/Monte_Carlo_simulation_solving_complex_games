package com.diplomski.common.battle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
	public Battle getBattle(List<CharacterState> initialCharacterStates) {
		BoardState currentBoardState = boardStateProvider.getInitialBoardState(initialCharacterStates);
		Battle battle = Battle.builder().initialBoardState(currentBoardState).build();
		List<Round> rounds = new ArrayList<>();

		boolean battleComplete = false;
		do {
			Round round = roundProvider.getRound(currentBoardState);
			rounds.add(round);

			currentBoardState = round.getFinalBoardState();

			battleComplete = getPartyHp(currentBoardState.getCharacterStates(), Party.PLAYER) == 0
					|| getPartyHp(currentBoardState.getCharacterStates(), Party.ENEMY) == 0;
		} while (!battleComplete);

		battle.setRounds(rounds);
		battle.setFinalBoardState(currentBoardState);
		return battle;
	}

	private int getPartyHp(LinkedHashMap<String, CharacterState> characterStates, Party party) {
		return characterStates.values().stream().filter(x -> x.getParty().equals(party)).mapToInt(x -> x.getCurrentHp()).sum();
	}
}
