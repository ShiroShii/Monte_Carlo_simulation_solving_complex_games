package com.diplomski.common.damage;

import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.resource.IResource;

public interface IDamageProvider {
	public int getDamage(IResource resource, BattleCharacterState initiator, BattleCharacterState target);
}
