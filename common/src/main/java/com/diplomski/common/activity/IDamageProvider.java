package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;

public interface IDamageProvider {
	public int getDamage(IResource resource, BattleCharacterState initiator, BattleCharacterState target);
}
