package com.diplomski.common.activity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.targeting.ITargetProvider;

public class AttackActionActivityProviderTest {
	private ITargetProvider targetProviderMock = mock(ITargetProvider.class);
	private IAttackRollOutcomeProvider attackRollOutcomeProviderMock = mock(IAttackRollOutcomeProvider.class);
	private IDamageProvider damageProviderMock = mock(IDamageProvider.class);
	private IWeaponProvider weaponProviderMock = mock(IWeaponProvider.class);

	private final int INITIATOR_INDEX = 0;
	private final int TARGET_INDEX = 1;
	private final int MISS_DAMAGE = 0;
	private final int HIT_DAMAGE = 10;
	private final int CRITICAL_HIT_DAMAGE = 20;

	private final int TARGET_INITIAL_HP = 15;
	private final int TARGET_FINAL_HIT_HP = 5;
	private final int TARGET_FINAL_CRITICAL_HIT_HP = 0;

	private CharacterState initiator;
	private CharacterState targetInitialState;
	private CharacterState targetFinalHitState;
	private CharacterState targetFinalCriticalHitState;

	private List<CharacterState> initialCharacterStates;
	private List<CharacterState> finalHitCharacterStates;
	private List<CharacterState> finalCriticalHitCharacterStates;

	private BoardState initialBoardState;
	private BoardState finalHitBoardState;
	private BoardState finalCriticalHitBoardState;

	private AttackActionActivity expectedMissActivity;
	private AttackActionActivity expectedHitActivity;
	private AttackActionActivity expectedCriticalHitActivity;

	private AttackActionActivityProvider unitUnderTest;

	@Before
	public void setup() {
		when(targetProviderMock.getTargetCharacterIndex(anyInt(), any())).thenReturn(TARGET_INDEX);
		when(damageProviderMock.getDamage(any(), any(), any())).thenReturn(HIT_DAMAGE);
		when(weaponProviderMock.getWeapon(any())).thenReturn(Weapon.CLUB);

		initiator = CharacterState.builder().build();
		targetInitialState = CharacterState.builder().currentHp(TARGET_INITIAL_HP).build();
		targetFinalHitState = CharacterState.builder().currentHp(TARGET_FINAL_HIT_HP).build();
		targetFinalCriticalHitState = CharacterState.builder().currentHp(TARGET_FINAL_CRITICAL_HIT_HP).build();

		initialCharacterStates = Arrays.asList(initiator, targetInitialState);
		finalHitCharacterStates = Arrays.asList(initiator, targetFinalHitState);
		finalCriticalHitCharacterStates = Arrays.asList(initiator, targetFinalCriticalHitState);

		initialBoardState = BoardState.builder().characterStates(initialCharacterStates).build();
		finalHitBoardState = BoardState.builder().characterStates(finalHitCharacterStates).build();
		finalCriticalHitBoardState = BoardState.builder().characterStates(finalCriticalHitCharacterStates).build();

		expectedMissActivity = AttackActionActivity.builder().damage(MISS_DAMAGE).initialBoardState(initialBoardState)
				.finalBoardState(initialBoardState).initiatingCharacterIndex(INITIATOR_INDEX)
				.targetCharacterIndex(TARGET_INDEX).build();

		expectedHitActivity = AttackActionActivity.builder().damage(HIT_DAMAGE).initialBoardState(initialBoardState)
				.finalBoardState(finalHitBoardState).initiatingCharacterIndex(INITIATOR_INDEX)
				.targetCharacterIndex(TARGET_INDEX).build();

		expectedCriticalHitActivity = AttackActionActivity.builder().damage(CRITICAL_HIT_DAMAGE)
				.initialBoardState(initialBoardState).finalBoardState(finalCriticalHitBoardState)
				.initiatingCharacterIndex(INITIATOR_INDEX).targetCharacterIndex(TARGET_INDEX).build();

		unitUnderTest = new AttackActionActivityProvider(targetProviderMock, attackRollOutcomeProviderMock,
				damageProviderMock, weaponProviderMock);
	}

	@Test
	public void testGetActivity_fumble() {
		when(attackRollOutcomeProviderMock.getAttackOutcome(any(), any(), any())).thenReturn(AttackRollOutcome.FUMBLE);

		Activity result = unitUnderTest.getActivity(0, initialBoardState);

		assertEquals(expectedMissActivity, result);
	}

	@Test
	public void testGetActivity_miss() {
		when(attackRollOutcomeProviderMock.getAttackOutcome(any(), any(), any())).thenReturn(AttackRollOutcome.MISS);

		Activity result = unitUnderTest.getActivity(0, initialBoardState);

		assertEquals(expectedMissActivity, result);
	}

	@Test
	public void testGetActivity_hit() {
		when(attackRollOutcomeProviderMock.getAttackOutcome(any(), any(), any())).thenReturn(AttackRollOutcome.HIT);

		Activity result = unitUnderTest.getActivity(0, initialBoardState);

		assertEquals(expectedHitActivity, result);
	}

	@Test
	public void testGetActivity_criticalHit() {
		when(attackRollOutcomeProviderMock.getAttackOutcome(any(), any(), any()))
				.thenReturn(AttackRollOutcome.CRITICAL_HIT);

		Activity result = unitUnderTest.getActivity(0, initialBoardState);

		assertEquals(expectedCriticalHitActivity, result);
	}
}
