package com.diplomski.common.character;

import static com.diplomski.common.resource.WeaponCategory.SIMPLE_MELEE;
import static com.diplomski.common.resource.WeaponCategory.SIMPLE_RANGED;
import static com.diplomski.common.resource.WeaponCategory.MARTIAL_MELEE;
import static com.diplomski.common.resource.WeaponCategory.MARTIAL_RANGED;

import java.util.EnumSet;

import com.diplomski.common.resource.WeaponCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CharacterClass {
	BARBARIAN(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED, MARTIAL_MELEE, MARTIAL_RANGED)),
	BARD(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED, MARTIAL_RANGED)),
	CLERIC(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED, MARTIAL_MELEE, MARTIAL_RANGED)),
	DRUID(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED)),
	FIGHTER(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED, MARTIAL_MELEE, MARTIAL_RANGED)),
	MONK(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED)),
	PALADIN(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED, MARTIAL_MELEE, MARTIAL_RANGED)),
	RANGER(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED, MARTIAL_MELEE, MARTIAL_RANGED)),
	ROGUE(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED, MARTIAL_RANGED)),
	SORCERER(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED)),
	WARLOCK(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED)),
	WIZARD(EnumSet.of(SIMPLE_MELEE, SIMPLE_RANGED));

	@Getter
	private final EnumSet<WeaponCategory> weaponProficiencies;
}
