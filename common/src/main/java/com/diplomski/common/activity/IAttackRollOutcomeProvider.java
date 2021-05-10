package com.diplomski.common.activity;

import com.diplomski.common.character.CharacterState;

public interface IAttackRollOutcomeProvider {
	public AttackRollOutcome getAttackOutcome(Weapon weapon, CharacterState initiator, CharacterState target);
}
