package com.diplomski.common.damage;

import com.diplomski.common.character.BattleCharacterState;

public interface IArmorClassProvider {
	public int getArmorClass(BattleCharacterState target);
}
