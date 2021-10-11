package com.diplomski.common.resource;

import static com.diplomski.common.resource.CombatStyle.MELEE;
import static com.diplomski.common.resource.CombatStyle.RANGED;
import static com.diplomski.common.resource.WeaponDifficulty.MARTIAL;
import static com.diplomski.common.resource.WeaponDifficulty.SIMPLE;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum WeaponCategory {
	SIMPLE_MELEE(SIMPLE, MELEE),
	SIMPLE_RANGED(SIMPLE, RANGED),
	MARTIAL_MELEE(MARTIAL, MELEE),
	MARTIAL_RANGED(MARTIAL, RANGED);

	@Getter
	private WeaponDifficulty difficulty;
	@Getter
	private CombatStyle style;
}
