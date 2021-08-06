package com.diplomski.common.damage;

import com.diplomski.common.character.IBattleCharacterState;

public class ArmorClassProvider implements IArmorClassProvider {

	@Override
	public int getArmorClass(IBattleCharacterState target) {
		return target.getArmorClass();
	}
}
