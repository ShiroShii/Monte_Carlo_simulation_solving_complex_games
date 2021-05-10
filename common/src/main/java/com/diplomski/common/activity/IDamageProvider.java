package com.diplomski.common.activity;

import com.diplomski.common.character.CharacterState;

public interface IDamageProvider {
	public int getDamage(Weapon weapon, CharacterState initiator, CharacterState target);
}
