package com.diplomski.common.board;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.diplomski.common.character.BattlePlayerCharacterState;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.dice.IDice;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.turn.ITurnProvider;
import com.diplomski.common.turn.ITurnProviderFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardStateProvider implements IBoardStateProvider {
	private final ITurnProviderFactory turnProviderFactory;
	private final IDiceFactory diceFactory;

	@Override
	public BoardState getInitialBoardState(List<CharacterState> characters) {
		List<Entry<IBattleCharacterState, Integer>> initiatives = new ArrayList<>();

		for (CharacterState initialCharacterState : characters) {
			IDice dice = diceFactory.getD20();
			int initiative = dice.getRoll() + initialCharacterState.getDexterity();

			BattlePlayerCharacterState characterState = BattlePlayerCharacterState.toBuilder(initialCharacterState).build();

			initiatives.add(Map.entry(characterState, initiative));
		}

		initiatives.sort(Entry.<IBattleCharacterState, Integer>comparingByValue().reversed());

		LinkedHashMap<String, IBattleCharacterState> sortedCharacterStates = new LinkedHashMap<>();

		for (Entry<IBattleCharacterState, Integer> initiative : initiatives) {
			IBattleCharacterState characterState = initiative.getKey();
			sortedCharacterStates.put(characterState.getId(), characterState);
		}

		BoardState boardState = BoardState.builder().characterStates(sortedCharacterStates).build();

		// Add turn providers
		for (IBattleCharacterState battleCharacterState : boardState.getCharacterStates().values()) {
			ITurnProvider turnProvider = turnProviderFactory.getTurnProvider(battleCharacterState.getId(), boardState);
			battleCharacterState.setTurnProvider(turnProvider);
		}

		return boardState;
	}
}
