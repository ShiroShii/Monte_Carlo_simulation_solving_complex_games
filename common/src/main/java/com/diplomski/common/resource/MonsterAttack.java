package com.diplomski.common.resource;

import com.diplomski.common.damage.DamageRoll;
import com.diplomski.common.damage.DamageType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonsterAttack implements IResource{
	private final String name;
	private final  CombatStyle combatStyle;
	private final int attackRollModifier;
	private final DamageType damageType;
	private final DamageRoll damageRoll;
}
