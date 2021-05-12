package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;

public interface IDamageProvider {
	public int getDamage(Weapon weapon, BattleCharacterState initiator, BattleCharacterState target);
}
