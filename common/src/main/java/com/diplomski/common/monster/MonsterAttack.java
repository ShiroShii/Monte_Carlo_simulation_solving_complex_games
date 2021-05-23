package com.diplomski.common.monster;

import com.diplomski.common.damage.DamageType;
import com.diplomski.common.resource.CombatStyle;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonsterAttack {
	private String name;
	private CombatStyle combatStyle;
	private int attackRollModifier;
	private int damage;
	private DamageType damageType;
}
