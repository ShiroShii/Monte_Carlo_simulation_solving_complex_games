package com.diplomski.common.activity;

import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.resource.IResource;

import lombok.NonNull;

public interface IAttackRollModifierProvider {
	public int getAttackRollModifier(IResource resource, @NonNull IBattleCharacterState initiator);
}
