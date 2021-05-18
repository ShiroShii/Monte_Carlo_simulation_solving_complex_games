package com.diplomski.common.damage;

import com.diplomski.common.character.BattleCharacterState;

public interface IDamageRollProvider {
	public int getDamageRoll(BattleCharacterState characterState);
}
