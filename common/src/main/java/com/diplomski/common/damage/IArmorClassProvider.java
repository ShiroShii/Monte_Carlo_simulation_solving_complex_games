package com.diplomski.common.damage;

import com.diplomski.common.character.IBattleCharacterState;

import lombok.NonNull;

public interface IArmorClassProvider {
	public int getArmorClass(@NonNull IBattleCharacterState target);
}
