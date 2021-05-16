package com.diplomski.common.resource;

import static com.diplomski.common.resource.DamageType.BLUDGEONING;
import static com.diplomski.common.resource.DamageType.PIERCING;
import static com.diplomski.common.resource.WeaponCategory.SIMPLE_MELEE;
import static com.diplomski.common.resource.WeaponProperty.FINESSE;
import static com.diplomski.common.resource.WeaponProperty.LIGHT;
import static com.diplomski.common.resource.WeaponProperty.THROWN;

import java.util.EnumSet;

import com.diplomski.common.activity.IResource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Weapon implements IResource {
	CLUB(SIMPLE_MELEE, BLUDGEONING, EnumSet.of(LIGHT)),
	DAGGER(SIMPLE_MELEE, PIERCING, EnumSet.of(FINESSE, LIGHT, THROWN));

	@Getter
	private final WeaponCategory weaponCategory;

	@Getter
	private final DamageType damageType;

	@Getter
	private final EnumSet<WeaponProperty> properties;
}
