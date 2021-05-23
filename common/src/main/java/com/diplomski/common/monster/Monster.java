package com.diplomski.common.monster;

import static com.diplomski.common.damage.DamageType.PIERCING;
import static com.diplomski.common.resource.CombatStyle.MELEE;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Monster {
	PANTHER(
			12,
			13,
			50,
			Arrays.asList(MonsterAttack.builder().name("Bite").combatStyle(MELEE).attackRollModifier(4).damage(5)
					.damageType(PIERCING).build())),
	GIANT_RAT(
			12,
			7,
			30,
			Arrays.asList(MonsterAttack.builder().name("Bite").combatStyle(MELEE).attackRollModifier(4).damage(4)
					.damageType(PIERCING).build()));

	@Getter
	private int armorClass;
	@Getter
	private int maxHp;
	@Getter
	private int walkingSpeed;
	@Getter
	private List<MonsterAttack> attack;
}
