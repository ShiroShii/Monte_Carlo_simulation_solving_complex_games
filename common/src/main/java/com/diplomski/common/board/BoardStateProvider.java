package com.diplomski.common.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.diplomski.common.character.CharacterState;
import com.diplomski.common.dice.IDice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardStateProvider implements IBoardStateProvider {
	private final IDice dice;

	@Override
	public BoardState getInitialBoardState(List<CharacterState> characters) {
		List<Entry<CharacterState, Integer>> initiatives = new ArrayList<>();
		
		for(CharacterState characterState: characters) {
			int initiative = dice.getRoll() + characterState.getDex();
			
			initiatives.add(Map.entry(characterState, initiative));
		}
		
		initiatives.sort(Entry.<CharacterState, Integer>comparingByValue().reversed());
		
		List<CharacterState> sortedCharacterStates = new ArrayList<>();
		
		for(Entry<CharacterState, Integer> initiative: initiatives) {
			sortedCharacterStates.add(initiative.getKey());
		}
		
		return BoardState.builder().characterStates(sortedCharacterStates).build();
	}
}
