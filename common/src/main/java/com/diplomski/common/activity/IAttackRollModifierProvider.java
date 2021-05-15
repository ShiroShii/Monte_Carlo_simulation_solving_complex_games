package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;

public interface IAttackRollModifierProvider {
	public int getAttackRollModifier(IResource resource, BattleCharacterState initiator);
}
