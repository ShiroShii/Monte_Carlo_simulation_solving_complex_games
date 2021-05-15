package com.diplomski.common.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Weapon implements IResource {
	CLUB(WeaponDificulty.SIMPLE, DamageType.BLUDGEONING, WeaponStyle.MELEE),
	DAGGER(WeaponDificulty.SIMPLE, DamageType.PIERCING, WeaponStyle.MELEE);

	@Getter
	private final WeaponDificulty weaponDificulty;

	@Getter
	private final DamageType damageType;

	@Getter
	private final WeaponStyle weaponStyle;
}
