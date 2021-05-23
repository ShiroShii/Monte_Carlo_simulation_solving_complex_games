package com.diplomski.common.damage;

import com.diplomski.common.character.BattleCharacterState;

public class ArmorClassProvider implements IArmorClassProvider {

	@Override
	public int getArmorClass(BattleCharacterState target) {
		return target.getArmorClass();
	}
}
