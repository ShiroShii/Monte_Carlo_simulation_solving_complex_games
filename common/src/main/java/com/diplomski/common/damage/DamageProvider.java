package com.diplomski.common.damage;

import com.diplomski.common.activity.IResource;
import com.diplomski.common.character.BattleCharacterState;

public class DamageProvider implements IDamageProvider {

	@Override
	public int getDamage(IResource resource, BattleCharacterState initiator, BattleCharacterState target) {
		// TODO: Implement DamageProvider getDamage
		return 10;
	}

}
