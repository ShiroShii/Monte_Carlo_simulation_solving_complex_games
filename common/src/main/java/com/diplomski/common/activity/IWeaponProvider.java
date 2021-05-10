package com.diplomski.common.activity;

import com.diplomski.common.character.CharacterState;

public interface IWeaponProvider {
	public Weapon getWeapon(CharacterState characterState);
}
