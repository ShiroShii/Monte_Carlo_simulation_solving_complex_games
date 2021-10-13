package com.diplomski.common.resource;

import static com.diplomski.common.damage.DamageType.BLUDGEONING;
import static com.diplomski.common.damage.DamageType.PIERCING;
import static com.diplomski.common.damage.DamageType.SLASHING;
import static com.diplomski.common.dice.DiceType.D12;
import static com.diplomski.common.dice.DiceType.D4;
import static com.diplomski.common.dice.DiceType.D8;
import static com.diplomski.common.resource.WeaponCategory.MARTIAL_MELEE;
import static com.diplomski.common.resource.WeaponCategory.MARTIAL_RANGED;
import static com.diplomski.common.resource.WeaponCategory.SIMPLE_MELEE;
import static com.diplomski.common.resource.WeaponProperty.FINESSE;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;

import com.diplomski.common.damage.DamageRoll;
import com.diplomski.common.damage.DamageType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Weapon implements IResource {
	CLUB(
			SIMPLE_MELEE,
			BLUDGEONING,
			EnumSet.noneOf(WeaponProperty.class),
			DamageRoll.builder().dice(Arrays.asList(D4)).build(),
			5,
			Optional.empty()),
	DAGGER(
			SIMPLE_MELEE,
			PIERCING,
			EnumSet.of(FINESSE),
			DamageRoll.builder().dice(Arrays.asList(D4)).build(),
			5,
			Optional.empty()),
	GREATAXE(
			MARTIAL_MELEE,
			SLASHING,
			EnumSet.noneOf(WeaponProperty.class),
			DamageRoll.builder().dice(Arrays.asList(D12)).build(),
			5,
			Optional.empty()),
	LONGSWORD(
			MARTIAL_MELEE,
			SLASHING,
			EnumSet.noneOf(WeaponProperty.class),
			DamageRoll.builder().dice(Arrays.asList(D8)).build(),
			5,
			Optional.empty()),
	WARHAMMER(
			MARTIAL_MELEE,
			BLUDGEONING,
			EnumSet.noneOf(WeaponProperty.class),
			DamageRoll.builder().dice(Arrays.asList(D8)).build(),
			5,
			Optional.empty()),
	LONGBOW(
			MARTIAL_RANGED,
			PIERCING,
			EnumSet.noneOf(WeaponProperty.class),
			DamageRoll.builder().dice(Arrays.asList(D8)).build(),
			150,
			Optional.of(600));

	@Getter
	private final WeaponCategory weaponCategory;

	@Getter
	private final DamageType damageType;

	@Getter
	private final EnumSet<WeaponProperty> properties;

	@Getter
	private final DamageRoll damageRoll;

	@Getter
	private final int range;

	@Getter
	private final Optional<Integer> longRange;

	@Override
	public CombatStyle getCombatStyle() {
		return this.weaponCategory.getStyle();
	}
}
