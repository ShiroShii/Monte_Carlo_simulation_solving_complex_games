package com.diplomski.common.test.unit.targeting;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.BattlePlayerCharacterState;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.targeting.RoundRobinTargetProvider;

public class RoundRobinTargetProviderTest {
	private final String INITIATOR_ID = "Initiator Id";
	private final String ENEMY_1_ID = "Enemy 1 Id";
	private final String ENEMY_2_ID = "Enemy 1 Id";
	private final Party INITIATOR_PARTY = Party.PLAYER;
	private final Party TARGET_PARTY = Party.ENEMY;
	private BoardState boardStateWithTarget;
	private BoardState boardStateWithoutTarget;

	private BattlePlayerCharacterState initiatorCharacterState;
	private BattlePlayerCharacterState enemyTargetCharacterState;
	private BattlePlayerCharacterState enemyIncapacitatedCharacterState;

	private LinkedHashMap<String, IBattleCharacterState> characterStatesWithTarget;
	private LinkedHashMap<String, IBattleCharacterState> characterStatesWithoutTarget;

	RoundRobinTargetProvider unitUnderTest;

	@Before
	public void setup() {
		initiatorCharacterState = BattlePlayerCharacterState.builder().id(INITIATOR_ID).currentHp(50).party(INITIATOR_PARTY)
				.build();
		enemyTargetCharacterState = BattlePlayerCharacterState.builder().id(ENEMY_1_ID).currentHp(50).party(TARGET_PARTY)
				.build();
		enemyIncapacitatedCharacterState = BattlePlayerCharacterState.builder().id(ENEMY_2_ID).currentHp(0)
				.party(TARGET_PARTY).build();

		characterStatesWithTarget = new LinkedHashMap<>();
		characterStatesWithTarget.put(INITIATOR_ID, initiatorCharacterState);
		characterStatesWithTarget.put(ENEMY_1_ID, enemyIncapacitatedCharacterState);
		characterStatesWithTarget.put(ENEMY_2_ID, enemyTargetCharacterState);

		characterStatesWithoutTarget = new LinkedHashMap<>();
		characterStatesWithoutTarget.put(INITIATOR_ID, initiatorCharacterState);
		characterStatesWithoutTarget.put(ENEMY_1_ID, enemyIncapacitatedCharacterState);

		boardStateWithTarget = BoardState.builder().characterStates(characterStatesWithTarget).build();
		boardStateWithoutTarget = BoardState.builder().characterStates(characterStatesWithoutTarget).build();
		unitUnderTest = new RoundRobinTargetProvider();
	}

	@Test
	public void testGetTargetId_shouldGetTarget() {
		Optional<String> result = unitUnderTest.getTargetId(INITIATOR_ID, TARGET_PARTY, boardStateWithTarget);

		assertEquals(Optional.of(ENEMY_2_ID), result);
	}

	@Test
	public void testGetTargetId_shouldNotGetTarget() {
		Optional<String> result = unitUnderTest.getTargetId(INITIATOR_ID, TARGET_PARTY, boardStateWithoutTarget);

		assertEquals(Optional.empty(), result);
	}
}
