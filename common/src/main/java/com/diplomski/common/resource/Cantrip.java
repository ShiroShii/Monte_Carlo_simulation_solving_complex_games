package com.diplomski.common.resource;

import static com.diplomski.common.damage.DamageType.FIRE;
import static com.diplomski.common.damage.DamageType.LIGHTNING;
import static com.diplomski.common.dice.DiceType.D10;
import static com.diplomski.common.dice.DiceType.D8;
import static com.diplomski.common.resource.CombatStyle.MELEE;
import static com.diplomski.common.resource.CombatStyle.RANGED;

import java.util.Arrays;

import com.diplomski.common.damage.DamageRoll;
import com.diplomski.common.damage.DamageType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Cantrip implements IResource {
	SHOCKING_GRASP(MELEE, LIGHTNING, DamageRoll.builder().dice(Arrays.asList(D8)).build()),
	FIRE_BOLT(RANGED, FIRE, DamageRoll.builder().dice(Arrays.asList(D10)).build());

	@Getter
	private final CombatStyle combatStyle;

	@Getter
	private final DamageType damageType;

	@Getter
	// TODO: spell damage increases with level
	private final DamageRoll damageRoll;
}
