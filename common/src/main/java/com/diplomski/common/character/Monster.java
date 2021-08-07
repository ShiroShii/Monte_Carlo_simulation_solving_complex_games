package com.diplomski.common.character;

import static com.diplomski.common.damage.DamageType.PIERCING;
import static com.diplomski.common.damage.DamageType.SLASHING;
import static com.diplomski.common.resource.CombatStyle.MELEE;

import java.util.Arrays;
import java.util.List;

import com.diplomski.common.resource.IResource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Monster {
	PANTHER(
			12,
			13,
			50,
			Arrays.asList(MonsterAttack.builder().name("Bite").combatStyle(MELEE).attackRollModifier(4).damage(5)
					.damageType(PIERCING).build(), MonsterAttack.builder().name("Claw").combatStyle(MELEE)
							.attackRollModifier(4).damage(4).damageType(SLASHING).build()),
			15),
	GIANT_RAT(
			12,
			7,
			30,
			Arrays.asList(MonsterAttack.builder().name("Bite").combatStyle(MELEE).attackRollModifier(4).damage(4)
					.damageType(PIERCING).build()),
			15);

	@Getter
	private int armorClass;
	@Getter
	private int maxHp;
	@Getter
	private int walkingSpeed;
	@Getter
	private List<IResource> attack;
	@Getter
	private int dexterity;
}
