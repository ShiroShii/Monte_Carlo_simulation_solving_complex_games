package com.diplomski.common.character;

import static com.diplomski.common.resource.CombatStyle.MELEE;
import static com.diplomski.common.resource.CombatStyle.RANGED;

import com.diplomski.common.resource.CombatStyle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PlayStyle {
	MELEE_DAMAGE(MELEE),
	RANGED_DAMAGE(RANGED),
	SPELL_MELEE_DAMAGE(MELEE),
	SPELL_RANGED_DAMAGE(RANGED),
	SUPPORT(RANGED),
	BATTLEFIELD_CONTROL(RANGED),
	EVADE(RANGED);

	@Getter
	private final CombatStyle combatStyle;
}
