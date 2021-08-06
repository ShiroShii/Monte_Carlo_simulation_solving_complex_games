package com.diplomski.common.resource;

import com.diplomski.common.character.BattlePlayerCharacterState;

public interface IWeaponProvider {
	public Weapon getWeapon(BattlePlayerCharacterState characterState);
}
