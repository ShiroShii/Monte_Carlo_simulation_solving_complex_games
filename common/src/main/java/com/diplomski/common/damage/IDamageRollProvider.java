package com.diplomski.common.damage;

import com.diplomski.common.character.PlayerBattleCharacterState;

public interface IDamageRollProvider {
	public int getDamageRoll(PlayerBattleCharacterState characterState);
}
