package com.diplomski.common.resource;

import static com.diplomski.common.damage.DamageType.FIRE;
import static com.diplomski.common.damage.DamageType.LIGHTNING;
import static com.diplomski.common.resource.CombatStyle.MELEE;
import static com.diplomski.common.resource.CombatStyle.RANGED;

import com.diplomski.common.damage.DamageType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Cantrip implements IResource {
	SHOCKING_GRASP(MELEE, LIGHTNING),
	FIRE_BOLT(RANGED, FIRE);

	@Getter
	private final CombatStyle combatStyle;

	@Getter
	private final DamageType damageType;
}
