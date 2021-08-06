package com.diplomski.common.damage;

import com.diplomski.common.character.BattlePlayerCharacterState;

public interface IDamageRollProvider {
	public int getDamageRoll(BattlePlayerCharacterState characterState);
}
