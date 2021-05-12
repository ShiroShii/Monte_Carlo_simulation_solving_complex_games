package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;

public interface IWeaponProvider {
	public Weapon getWeapon(BattleCharacterState characterState);
}
