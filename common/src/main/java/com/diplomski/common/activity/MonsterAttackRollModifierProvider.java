package com.diplomski.common.activity;

import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.MonsterAttack;
import com.diplomski.common.resource.IResource;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MonsterAttackRollModifierProvider implements IAttackRollModifierProvider {
	@Override
	public int getAttackRollModifier(IResource resource, IBattleCharacterState initiator) {
		// TODO: Check distance
		return ((MonsterAttack) resource).getAttackRollModifier();
	}
}
