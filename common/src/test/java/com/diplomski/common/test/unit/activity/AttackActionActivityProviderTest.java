package com.diplomski.common.test.unit.activity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.activity.Activity;
import com.diplomski.common.activity.AttackActionActivity;
import com.diplomski.common.activity.AttackActionActivityProvider;
import com.diplomski.common.activity.AttackRollOutcome;
import com.diplomski.common.activity.IAttackRollOutcomeProvider;
import com.diplomski.common.activity.IDamageProvider;
import com.diplomski.common.activity.Weapon;
import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.character.Party;

public class AttackActionActivityProviderTest {
	private IAttackRollOutcomeProvider attackRollOutcomeProviderMock = mock(IAttackRollOutcomeProvider.class);
	private IDamageProvider damageProviderMock = mock(IDamageProvider.class);

	private final Weapon weapon = Weapon.CLUB;
	private final String INITIATOR_ID = "Initiator Id";
	private final String TARGET_ID = "Target Id";
	private final int MISS_DAMAGE = 0;
	private final int HIT_DAMAGE = 10;
	private final int CRITICAL_HIT_DAMAGE = 20;

	private final int TARGET_INITIAL_HP = 15;
	private final int TARGET_FINAL_HIT_HP = 5;
	private final int TARGET_FINAL_CRITICAL_HIT_HP = 0;

	private BattleCharacterState initiator;
	private BattleCharacterState targetInitialState;
	private BattleCharacterState targetFinalHitState;
	private BattleCharacterState targetFinalCriticalHitState;

	private LinkedHashMap<String, BattleCharacterState> initialCharacterStates;
	private LinkedHashMap<String, BattleCharacterState> finalHitCharacterStates;
	private LinkedHashMap<String, BattleCharacterState> finalCriticalHitCharacterStates;

	private BoardState initialBoardState;
	private BoardState finalHitBoardState;
	private BoardState finalCriticalHitBoardState;

	private AttackActionActivity expectedMissActivity;
	private AttackActionActivity expectedHitActivity;
	private AttackActionActivity expectedCriticalHitActivity;

	private AttackActionActivityProvider unitUnderTest;

	@Before
	public void setup() {
		when(damageProviderMock.getDamage(any(), any(), any())).thenReturn(HIT_DAMAGE);

		initiator = BattleCharacterState.builder().party(Party.PLAYER).build();
		targetInitialState = BattleCharacterState.builder().party(Party.ENEMY).currentHp(TARGET_INITIAL_HP).build();
		targetFinalHitState = BattleCharacterState.builder().party(Party.ENEMY).currentHp(TARGET_FINAL_HIT_HP).build();
		targetFinalCriticalHitState = BattleCharacterState.builder().party(Party.ENEMY)
				.currentHp(TARGET_FINAL_CRITICAL_HIT_HP).build();

		initialCharacterStates = new LinkedHashMap<>();
		initialCharacterStates.put(INITIATOR_ID, initiator); 
		initialCharacterStates.put(TARGET_ID, targetInitialState);

		finalHitCharacterStates = new LinkedHashMap<>();
		finalHitCharacterStates.put(INITIATOR_ID, initiator); 
		finalHitCharacterStates.put(TARGET_ID, targetFinalHitState);
		
		finalCriticalHitCharacterStates = new LinkedHashMap<>();
		finalCriticalHitCharacterStates.put(INITIATOR_ID, initiator); 
		finalCriticalHitCharacterStates.put(TARGET_ID, targetFinalCriticalHitState);

		initialBoardState = BoardState.builder().characterStates(initialCharacterStates).build();
		finalHitBoardState = BoardState.builder().characterStates(finalHitCharacterStates).build();
		finalCriticalHitBoardState = BoardState.builder().characterStates(finalCriticalHitCharacterStates).build();

		expectedMissActivity = AttackActionActivity.builder().damage(MISS_DAMAGE).initialBoardState(initialBoardState)
				.finalBoardState(initialBoardState).initiatorId(INITIATOR_ID)
				.targetId(TARGET_ID).build();

		expectedHitActivity = AttackActionActivity.builder().damage(HIT_DAMAGE).initialBoardState(initialBoardState)
				.finalBoardState(finalHitBoardState).initiatorId(INITIATOR_ID)
				.targetId(TARGET_ID).build();

		expectedCriticalHitActivity = AttackActionActivity.builder().damage(CRITICAL_HIT_DAMAGE)
				.initialBoardState(initialBoardState).finalBoardState(finalCriticalHitBoardState)
				.initiatorId(INITIATOR_ID).targetId(TARGET_ID).build();

		unitUnderTest = new AttackActionActivityProvider(attackRollOutcomeProviderMock, damageProviderMock);
	}

	@Test
	public void testGetActivity_fumble() {
		when(attackRollOutcomeProviderMock.getAttackOutcome(any(), any(), any())).thenReturn(AttackRollOutcome.FUMBLE);

		Activity result = unitUnderTest.getActivity(INITIATOR_ID, TARGET_ID, initialBoardState);

		assertEquals(expectedMissActivity, result);
	}

	@Test
	public void testGetActivity_miss() {
		when(attackRollOutcomeProviderMock.getAttackOutcome(any(), any(), any())).thenReturn(AttackRollOutcome.MISS);

		Activity result = unitUnderTest.getActivity(INITIATOR_ID, TARGET_ID, initialBoardState);

		assertEquals(expectedMissActivity, result);
	}

	@Test
	public void testGetActivity_hit() {
		when(attackRollOutcomeProviderMock.getAttackOutcome(any(), any(), any())).thenReturn(AttackRollOutcome.HIT);

		Activity result = unitUnderTest.getActivity(INITIATOR_ID, TARGET_ID, initialBoardState, weapon);

		assertEquals(expectedHitActivity, result);
	}

	@Test
	public void testGetActivity_criticalHit() {
		when(attackRollOutcomeProviderMock.getAttackOutcome(any(), any(), any()))
				.thenReturn(AttackRollOutcome.CRITICAL_HIT);

		Activity result = unitUnderTest.getActivity(INITIATOR_ID, TARGET_ID, initialBoardState, weapon);

		assertEquals(expectedCriticalHitActivity, result);
	}
}
