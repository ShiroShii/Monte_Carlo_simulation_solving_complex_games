package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.resource.IResource;

public interface IAttackRollModifierProvider {
	public int getAttackRollModifier(IResource resource, BattleCharacterState initiator);
}
