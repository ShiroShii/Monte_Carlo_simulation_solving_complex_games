package com.diplomski.common.damage;

import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.resource.IResource;

public class DamageProvider implements IDamageProvider {

	@Override
	public int getDamage(IResource resource, IBattleCharacterState initiator, IBattleCharacterState target) {
		// TODO: Implement DamageProvider getDamage
		return 10;
	}

}
