package com.diplomski.common.board;

import static com.diplomski.common.character.CharacterType.MONSTER;
import static com.diplomski.common.character.CharacterType.PLAYER;
import static com.diplomski.common.character.ICharacterState.getModifier;
import static com.diplomski.common.dice.DiceType.D20;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	private final static Collector<
			Entry<IBattleCharacterState, Integer>,
			?,
			LinkedHashMap<UUID, IBattleCharacterState>> linkedHashMapCollector =
					Collectors.toMap(
							x -> x.getKey().getId(),
							x -> x.getKey(),
							(v1, v2) -> {
								throw new AssertionError();
							},
							LinkedHashMap::new);
	
	private static final Comparator<Entry<IBattleCharacterState, Integer>> reverseSortComparator =
			(c1, c2) -> -c1.getValue().compareTo(c2.getValue());

	@Override
	public BoardState getInitialBoardState(
			List<ICharacterState> characters,
			IBoard board) {
		LinkedHashMap<UUID, IBattleCharacterState> initiatives =
				characters.stream()
						.map(getInitiative(diceFactory.getDice(D20)))
						.sorted(reverseSortComparator)
						.collect(linkedHashMapCollector);

		return BoardState.builder()
				.board(board)
				.characterStates(initiatives)
				.build();
	}

	private Function<
			ICharacterState,
			Entry<IBattleCharacterState, Integer>> getInitiative(IDice dice) {
		return character -> getInitiative(dice, character);
	}

	private Entry<IBattleCharacterState, Integer> getInitiative(
			IDice dice,
			ICharacterState character) {
		int initiative = dice.getRoll() + getModifier(character.getDexterity());

		CharacterType characterType =
				character instanceof PlayerCharacterState ?
						PLAYER :
						MONSTER;

		ITurnProvider turnProvider = turnProviderFactory.getTurnProvider(
				character.getId(),
				character.getParty(),
				character.getPlayStyle(),
				character.getTargetingStyle(),
				characterType);

		IBattleCharacterState characterState = switch (characterType) {
			case PLAYER -> PlayerBattleCharacterState.getBattleState(
					(PlayerCharacterState) character,
					turnProvider);
			case MONSTER -> MonsterBattleCharacterState.getBattleState(
					(MonsterCharacterState) character,
					turnProvider);
		};

		characterState.setTurnProvider(turnProvider);
		return Map.entry(characterState, initiative);
	}
}
