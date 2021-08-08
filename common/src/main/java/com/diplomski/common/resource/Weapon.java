package com.diplomski.common.resource;

import static com.diplomski.common.damage.DamageType.BLUDGEONING;
import static com.diplomski.common.damage.DamageType.PIERCING;
import static com.diplomski.common.dice.DiceType.D4;
import static com.diplomski.common.resource.WeaponCategory.SIMPLE_MELEE;
import static com.diplomski.common.resource.WeaponProperty.FINESSE;
import static com.diplomski.common.resource.WeaponProperty.LIGHT;
import static com.diplomski.common.resource.WeaponProperty.THROWN;

import java.util.Arrays;
import java.util.EnumSet;

import com.diplomski.common.damage.DamageRoll;
import com.diplomski.common.damage.DamageType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Weapon implements IResource {
	CLUB(SIMPLE_MELEE, BLUDGEONING, EnumSet.of(LIGHT), DamageRoll.builder().dice(Arrays.asList(D4)).build()),
	DAGGER(SIMPLE_MELEE, PIERCING, EnumSet.of(FINESSE, LIGHT, THROWN), DamageRoll.builder().dice(Arrays.asList(D4)).build());

	@Getter
	private final WeaponCategory weaponCategory;

	@Getter
	private final DamageType damageType;

	@Getter
	private final EnumSet<WeaponProperty> properties;

	@Getter
	private final DamageRoll damageRoll;

	@Override
	public CombatStyle getCombatStyle() {
		return this.weaponCategory.getStyle();
	}
}
