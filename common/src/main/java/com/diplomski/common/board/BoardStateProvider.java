package com.diplomski.common.board;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.dice.IDice;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.turn.ITurnProvider;
import com.diplomski.common.turn.ITurnProviderFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardStateProvider implements IBoardStateProvider {
	private final ITurnProviderFactory turnProviderFactory;
	private final IDiceFactory diceFactory;

	@Override
	public BoardState getInitialBoardState(List<CharacterState> characters) {
		List<Entry<BattleCharacterState, Integer>> initiatives = new ArrayList<>();

		for (CharacterState initialCharacterState : characters) {
			IDice dice = diceFactory.getD20();
			// TODO: add Initiative Modifiers to initiative roll
			int initiative = dice.getRoll() + initialCharacterState.getDexterity();

			BattleCharacterState characterState = BattleCharacterState.builder().id(initialCharacterState.getId())
					.currentHp(initialCharacterState.getCurrentHp()).dexterity(initialCharacterState.getDexterity())
					.exhaustionLevel(initialCharacterState.getExhaustionLevel()).maxHp(initialCharacterState.getMaxHp())
					.party(initialCharacterState.getParty()).tile(initialCharacterState.getTile())
					.walkingSpeed(initialCharacterState.getWalkingSpeed()).usedWalkingSpeed(0).build();

			initiatives.add(Map.entry(characterState, initiative));
		}

		initiatives.sort(Entry.<BattleCharacterState, Integer>comparingByValue().reversed());

		LinkedHashMap<String, BattleCharacterState> sortedCharacterStates = new LinkedHashMap<>();

		for (Entry<BattleCharacterState, Integer> initiative : initiatives) {
			BattleCharacterState characterState = initiative.getKey();
			sortedCharacterStates.put(characterState.getId(), characterState);
		}

		BoardState boardState = BoardState.builder().characterStates(sortedCharacterStates).build();

		// Add turn providers
		for (BattleCharacterState battleCharacterState : boardState.getCharacterStates().values()) {
			ITurnProvider turnProvider = turnProviderFactory.getTurnProvider(battleCharacterState.getId(), boardState);
			battleCharacterState.setTurnProvider(turnProvider);
		}

		return boardState;
	}
}
