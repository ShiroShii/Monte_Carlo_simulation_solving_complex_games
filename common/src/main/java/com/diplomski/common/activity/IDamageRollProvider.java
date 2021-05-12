package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;

public interface IDamageRollProvider {
	public int getDamageRoll(BattleCharacterState characterState);
}
