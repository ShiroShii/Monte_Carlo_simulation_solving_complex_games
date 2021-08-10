package com.diplomski.common.test.unit.targeting;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayerBattleCharacterState;
import com.diplomski.common.targeting.RoundRobinTargetProvider;

public class RoundRobinTargetProviderTest {
	private final UUID INITIATOR_ID = UUID.fromString("8b521099-18fd-4810-953d-bc4dde0eae14");
	private final UUID ENEMY_1_ID =  UUID.fromString("3e5aee3a-41e6-402c-a42d-6da8adc7cac9");
	private final UUID ENEMY_2_ID = UUID.fromString("5394c3eb-c5b3-4698-a05f-9e4298f58de7");
	private final Party INITIATOR_PARTY = Party.PLAYER;
	private final Party TARGET_PARTY = Party.ENEMY;
	private BoardState boardStateWithTarget;
	private BoardState boardStateWithoutTarget;

	private PlayerBattleCharacterState initiatorCharacterState;
	private PlayerBattleCharacterState enemyTargetCharacterState;
	private PlayerBattleCharacterState enemyIncapacitatedCharacterState;

	private LinkedHashMap<UUID, IBattleCharacterState> characterStatesWithTarget;
	private LinkedHashMap<UUID, IBattleCharacterState> characterStatesWithoutTarget;

	RoundRobinTargetProvider unitUnderTest;

	@Before
	public void setup() {
		initiatorCharacterState = PlayerBattleCharacterState.builder().id(INITIATOR_ID).currentHp(50).party(INITIATOR_PARTY)
				.build();
		enemyTargetCharacterState = PlayerBattleCharacterState.builder().id(ENEMY_1_ID).currentHp(50).party(TARGET_PARTY)
				.build();
		enemyIncapacitatedCharacterState = PlayerBattleCharacterState.builder().id(ENEMY_2_ID).currentHp(0)
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
		Optional<UUID> result = unitUnderTest.getTargetId(INITIATOR_ID, TARGET_PARTY, boardStateWithTarget);

		assertEquals(Optional.of(ENEMY_1_ID), result);
	}

	@Test
	public void testGetTargetId_shouldNotGetTarget() {
		Optional<UUID> result = unitUnderTest.getTargetId(INITIATOR_ID, TARGET_PARTY, boardStateWithoutTarget);

		assertEquals(Optional.empty(), result);
	}
}
