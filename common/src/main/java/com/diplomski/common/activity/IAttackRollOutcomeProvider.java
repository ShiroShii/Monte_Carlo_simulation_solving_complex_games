package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;

public interface IAttackRollOutcomeProvider {
	public AttackRollOutcome getAttackOutcome(Weapon weapon, BattleCharacterState initiator,
			BattleCharacterState target);
}
