package com.diplomski.common.resource;

import static com.diplomski.common.resource.CombatStyle.MELEE;
import static com.diplomski.common.resource.CombatStyle.RANGED;
import static com.diplomski.common.resource.DamageType.FIRE;
import static com.diplomski.common.resource.DamageType.LIGHTNING;

import com.diplomski.common.activity.IResource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Spell implements IResource {
	SHOCKING_GRASP(MELEE, LIGHTNING),
	FIRE_BOLT(RANGED, FIRE);

	@Getter
	private final CombatStyle combatStyle;

	@Getter
	private final DamageType damageType;
}
