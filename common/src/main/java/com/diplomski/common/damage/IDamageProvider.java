package com.diplomski.common.damage;

import com.diplomski.common.activity.IResource;
import com.diplomski.common.character.BattleCharacterState;

public interface IDamageProvider {
	public int getDamage(IResource resource, BattleCharacterState initiator, BattleCharacterState target);
}
