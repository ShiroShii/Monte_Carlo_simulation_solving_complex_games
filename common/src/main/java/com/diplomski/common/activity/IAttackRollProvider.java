package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;

public interface IAttackRollProvider {
	public int getAttackRoll(BattleCharacterState characterState);
}
