package com.diplomski.common.damage;

import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.resource.IResource;

public interface IDamageProvider {
	public int getDamage(IResource resource, IBattleCharacterState initiator, IBattleCharacterState target);
}
