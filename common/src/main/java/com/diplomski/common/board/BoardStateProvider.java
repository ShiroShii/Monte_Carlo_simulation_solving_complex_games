package com.diplomski.common.board;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.dice.IDice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardStateProvider implements IBoardStateProvider {
	private final IDice dice;

	@Override
	public BoardState getInitialBoardState(List<CharacterState> characters) {
		List<Entry<BattleCharacterState, Integer>> initiatives = new ArrayList<>();

		for (CharacterState initialCharacterState : characters) {
			int initiative = dice.getRoll() + initialCharacterState.getDex();

			// TODO: add TurnProvider to each character
			BattleCharacterState characterState = BattleCharacterState.builder().id(initialCharacterState.getId())
					.currentHp(initialCharacterState.getCurrentHp()).dex(initialCharacterState.getDex())
					.exhaustionLevel(initialCharacterState.getExhaustionLevel()).maxHp(initialCharacterState.getMaxHp())
					.party(initialCharacterState.getParty()).tile(initialCharacterState.getTile())
					.walkingSpeed(initialCharacterState.getWalkingSpeed()).usedWalkingSpeed(0).turnProvider(null)
					.build();

			initiatives.add(Map.entry(characterState, initiative));
		}

		initiatives.sort(Entry.<BattleCharacterState, Integer>comparingByValue().reversed());

		LinkedHashMap<String, BattleCharacterState> sortedCharacterStates = new LinkedHashMap<>();

		for (Entry<BattleCharacterState, Integer> initiative : initiatives) {
			BattleCharacterState characterState = initiative.getKey();
			sortedCharacterStates.put(characterState.getId(), characterState);
		}

		return BoardState.builder().characterStates(sortedCharacterStates).build();
	}
}
