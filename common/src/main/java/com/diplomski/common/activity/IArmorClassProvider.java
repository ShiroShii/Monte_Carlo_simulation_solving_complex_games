package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;

public interface IArmorClassProvider {
	public int getArmorClass(BattleCharacterState target);
}
