package com.diplomski.common.targeting;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.character.Party;

public class RoundRobinTargetProviderTest {
	private final int INITIATOR_INDEX = 0;
	private final int EXPECTED_TARGET_INDEX = 2;
	private final Party INITIATOR_PARTY = Party.PLAYER;
	private final Party TARGET_PARTY = Party.ENEMY;
	private BoardState boardStateWithTarget;
	private BoardState boardStateWithoutTarget;

	private CharacterState initiatorCharacterState;
	private CharacterState enemyTargetCharacterState;
	private CharacterState enemyIncapacitatedCharacterState;

	private List<CharacterState> characterStatesWithTarget;
	private List<CharacterState> characterStatesWithoutTarget;

	RoundRobinTargetProvider unitUnderTest;

	@Before
	public void setup() {
		initiatorCharacterState = CharacterState.builder().party(INITIATOR_PARTY).build();
		enemyTargetCharacterState = CharacterState.builder().currentHp(50).party(TARGET_PARTY).build();
		enemyIncapacitatedCharacterState = CharacterState.builder().currentHp(0).party(TARGET_PARTY).build();

		characterStatesWithTarget = Arrays.asList(initiatorCharacterState, enemyIncapacitatedCharacterState,
				enemyTargetCharacterState);

		characterStatesWithoutTarget = Arrays.asList(initiatorCharacterState, enemyIncapacitatedCharacterState);

		boardStateWithTarget = BoardState.builder().characterStates(characterStatesWithTarget).build();
		boardStateWithoutTarget = BoardState.builder().characterStates(characterStatesWithoutTarget).build();
		unitUnderTest = new RoundRobinTargetProvider();
	}

	@Test
	public void testGetTargetCharacterIndex_shouldGetTarget() {
		Optional<Integer> result = unitUnderTest.getTargetCharacterIndex(INITIATOR_INDEX, TARGET_PARTY,
				boardStateWithTarget);

		assertEquals(Optional.of(EXPECTED_TARGET_INDEX), result);
	}

	@Test
	public void testGetTargetCharacterIndex_shouldNotGetTarget() {
		Optional<Integer> result = unitUnderTest.getTargetCharacterIndex(INITIATOR_INDEX, TARGET_PARTY,
				boardStateWithoutTarget);

		assertEquals(Optional.empty(), result);
	}
}
