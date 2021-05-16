package com.diplomski.common.resource;

import com.diplomski.common.character.BattleCharacterState;

public interface IWeaponProvider {
	public Weapon getWeapon(BattleCharacterState characterState);
}
