package com.diplomski.common.board;

import static com.diplomski.common.character.CharacterType.MONSTER;
import static com.diplomski.common.character.CharacterType.PLAYER;
import static com.diplomski.common.dice.DiceType.D20;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.diplomski.common.character.CharacterType;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.ICharacterState;
import com.diplomski.common.character.MonsterBattleCharacterState;
import com.diplomski.common.character.MonsterCharacterState;
import com.diplomski.common.character.PlayerBattleCharacterState;
import com.diplomski.common.character.PlayerCharacterState;
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
	public BoardState getInitialBoardState(List<ICharacterState> characters) {
		List<Entry<IBattleCharacterState, Integer>> initiatives = new ArrayList<>();

		IDice dice = diceFactory.getDice(D20);
		for (ICharacterState initialCharacterState : characters) {
			int initiative = (dice.getRoll() + (int) Math.floor((initialCharacterState.getDexterity() - 10) / 2.0d));
			CharacterType characterType = initialCharacterState instanceof PlayerCharacterState ? PLAYER : MONSTER;

			ITurnProvider turnProvider = turnProviderFactory
					.getTurnProvider(initialCharacterState.getId(), initialCharacterState
							.getParty(), initialCharacterState
									.getPlayStyle(), initialCharacterState.getTargetingStyle(), characterType);

			IBattleCharacterState characterState = switch (characterType) {
				case PLAYER -> PlayerBattleCharacterState
						.getBattleState((PlayerCharacterState) initialCharacterState, turnProvider);
				case MONSTER -> MonsterBattleCharacterState
						.getBattleState((MonsterCharacterState) initialCharacterState, turnProvider);
			};

			characterState.setTurnProvider(turnProvider);

			initiatives.add(Map.entry(characterState, initiative));
		}

		initiatives.sort(Entry.<IBattleCharacterState, Integer>comparingByValue().reversed());

		LinkedHashMap<String, IBattleCharacterState> sortedCharacterStates = new LinkedHashMap<>();

		for (Entry<IBattleCharacterState, Integer> initiative : initiatives) {
			IBattleCharacterState characterState = initiative.getKey();
			sortedCharacterStates.put(characterState.getId(), characterState);
		}

		BoardState boardState = BoardState.builder().characterStates(sortedCharacterStates).build();

		return boardState;
	}
}
