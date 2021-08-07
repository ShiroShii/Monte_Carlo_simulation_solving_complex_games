package com.diplomski.common.character;

import com.diplomski.common.damage.DamageType;
import com.diplomski.common.resource.CombatStyle;
import com.diplomski.common.resource.IResource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonsterAttack implements IResource{
	private String name;
	private CombatStyle combatStyle;
	private int attackRollModifier;
	private int damage;
	private DamageType damageType;
}
