package com.diplomski.common.resource;

import static com.diplomski.common.resource.CombatStyle.MELEE;
import static com.diplomski.common.resource.CombatStyle.RANGED;
import static com.diplomski.common.resource.WeaponDificulty.MARTIAL;
import static com.diplomski.common.resource.WeaponDificulty.SIMPLE;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum WeaponCategory {
	SIMPLE_MELEE(SIMPLE, MELEE),
	SIMPLE_RANGED(SIMPLE, RANGED),
	MARTIAL_MELEE(MARTIAL, MELEE),
	MARTIAL_RANGED(MARTIAL, RANGED);

	@Getter
	private WeaponDificulty dificulty;
	@Getter
	private CombatStyle style;
}
