package com.diplomski.common.resource;

import com.diplomski.common.character.PlayerBattleCharacterState;

public interface IWeaponProvider {
	public Weapon getWeapon(PlayerBattleCharacterState characterState);
}
