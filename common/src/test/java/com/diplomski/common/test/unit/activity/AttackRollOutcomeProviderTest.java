package com.diplomski.common.test.unit.activity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.activity.AttackRollOutcome;
import com.diplomski.common.activity.AttackRollOutcomeProvider;
import com.diplomski.common.activity.IAttackRollModifierProvider;
import com.diplomski.common.activity.IResource;
import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.damage.IArmorClassProvider;
import com.diplomski.common.dice.IDice;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.resource.Weapon;

public class AttackRollOutcomeProviderTest {
	private final int FUMBLE = 1;
	private final int MISS = 2;
	private final int HIT = 19;
	private final int CRITICAL_HIT = 20;
	private final int ARMOR_CLASS = 10;
	private final int ATTACK_ROLL_MODIFIER = 5;

	private final IResource RESOURCE = Weapon.CLUB;
	private BattleCharacterState initiator;
	private BattleCharacterState target;

	private IAttackRollModifierProvider attackRollModifierProviderMock = mock(IAttackRollModifierProvider.class);
	private IArmorClassProvider armorClassProviderMock = mock(IArmorClassProvider.class);
	private IDiceFactory diceFactoryMock = mock(IDiceFactory.class);
	private IDice diceMock = mock(IDice.class);

	AttackRollOutcomeProvider unitUnderTest;

	@Before
	public void setup() {
		initiator = BattleCharacterState.builder().build();
		target = BattleCharacterState.builder().build();
		when(attackRollModifierProviderMock.getAttackRollModifier(any(), any())).thenReturn(ATTACK_ROLL_MODIFIER);
		when(armorClassProviderMock.getArmorClass(any())).thenReturn(ARMOR_CLASS);
		when(diceFactoryMock.getD20()).thenReturn(diceMock);
		unitUnderTest = new AttackRollOutcomeProvider(attackRollModifierProviderMock, armorClassProviderMock, diceFactoryMock);
	}

	@Test
	public void testGetAttackOutcome_fumble() {
		when(diceMock.getRoll()).thenReturn(FUMBLE);

		AttackRollOutcome result = unitUnderTest.getAttackOutcome(RESOURCE, initiator, target);

		assertEquals(AttackRollOutcome.FUMBLE, result);
	}

	@Test
	public void testGetAttackOutcome_miss() {
		when(diceMock.getRoll()).thenReturn(MISS);

		AttackRollOutcome result = unitUnderTest.getAttackOutcome(RESOURCE, initiator, target);

		assertEquals(AttackRollOutcome.MISS, result);
	}

	@Test
	public void testGetAttackOutcome_hit() {
		when(diceMock.getRoll()).thenReturn(HIT);

		AttackRollOutcome result = unitUnderTest.getAttackOutcome(RESOURCE, initiator, target);

		assertEquals(AttackRollOutcome.HIT, result);
	}

	@Test
	public void testGetAttackOutcome_criticalHit() {
		when(diceMock.getRoll()).thenReturn(CRITICAL_HIT);

		AttackRollOutcome result = unitUnderTest.getAttackOutcome(RESOURCE, initiator, target);

		assertEquals(AttackRollOutcome.CRITICAL_HIT, result);
	}
}
