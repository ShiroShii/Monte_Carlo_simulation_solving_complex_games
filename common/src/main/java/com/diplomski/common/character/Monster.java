package com.diplomski.common.character;

import static com.diplomski.common.damage.DamageType.PIERCING;
import static com.diplomski.common.damage.DamageType.SLASHING;
import static com.diplomski.common.dice.DiceType.D4;
import static com.diplomski.common.dice.DiceType.D6;
import static com.diplomski.common.resource.CombatStyle.MELEE;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.diplomski.common.damage.DamageRoll;
import com.diplomski.common.resource.IResource;
import com.diplomski.common.resource.MonsterAttack;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Monster {
	//TODO: HP roll
	PANTHER(
			12,
			10,
			0,
			Arrays.asList(MonsterAttack.builder().name("Bite").combatStyle(MELEE).attackRollModifier(4)
					.damageRoll(DamageRoll.builder().rollAddend(2).dice(Arrays.asList(D6)).build()).damageType(PIERCING)
					.meleeRange(Optional.of(1)).normalRangedRange(Optional.empty()).longRangedRange(Optional.empty())
					.build(), MonsterAttack.builder().name("Claw").combatStyle(MELEE).attackRollModifier(4)
							.damageRoll(DamageRoll.builder().rollAddend(2).dice(Arrays.asList(D4)).build())
							.damageType(SLASHING).meleeRange(Optional.of(1)).normalRangedRange(Optional.empty()).longRangedRange(Optional.empty()).build()),
			15),
	GIANT_RAT(
			12,
			6,
			0,
			Arrays.asList(MonsterAttack.builder().name("Bite").combatStyle(MELEE).attackRollModifier(4)
					.damageRoll(DamageRoll.builder().rollAddend(2).dice(Arrays.asList(D4)).build()).damageType(PIERCING)
					.meleeRange(Optional.of(1)).normalRangedRange(Optional.empty()).longRangedRange(Optional.empty()).build()),
			15),
	SWARM_OF_BATS(
			12,
			0,
			30,
			Arrays.asList(MonsterAttack.builder().name("Bite").combatStyle(MELEE).attackRollModifier(4)
					.damageRoll(DamageRoll.builder().rollAddend(2).dice(Arrays.asList(D4)).build()).damageType(PIERCING)
					.meleeRange(Optional.of(0)).normalRangedRange(Optional.empty()).longRangedRange(Optional.empty()).build()),
			15
			);

	@Getter
	private int armorClass;
	@Getter
	private int walkingSpeed;
	@Getter
	private int flayingSpeed;
	@Getter
	private List<IResource> attack;
	@Getter
	private int dexterity;
}
