package com.diplomski.common.activity;

import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.resource.IResource;
import com.diplomski.common.resource.MonsterAttack;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MonsterAttackRollModifierProvider implements IAttackRollModifierProvider {
	@Override
	public int getAttackRollModifier(IResource resource, IBattleCharacterState initiator) {
		return ((MonsterAttack) resource).getAttackRollModifier();
	}
}
