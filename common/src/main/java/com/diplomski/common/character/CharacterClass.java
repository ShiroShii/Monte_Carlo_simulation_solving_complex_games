package com.diplomski.common.character;

import static com.diplomski.common.resource.WeaponCategory.SIMPLE_MELEE;

import java.util.EnumSet;

import com.diplomski.common.resource.WeaponCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CharacterClass {
	// TODO: CharacterClass weaponProficiencies
	BARBARIAN(EnumSet.of(SIMPLE_MELEE)),
	BARD(EnumSet.of(SIMPLE_MELEE)),
	CLERIC(EnumSet.of(SIMPLE_MELEE)),
	DRUID(EnumSet.of(SIMPLE_MELEE)),
	FIGHTER(EnumSet.of(SIMPLE_MELEE)),
	MONK(EnumSet.of(SIMPLE_MELEE)),
	PALADIN(EnumSet.of(SIMPLE_MELEE)),
	RANGER(EnumSet.of(SIMPLE_MELEE)),
	ROGUE(EnumSet.of(SIMPLE_MELEE)),
	SORCERER(EnumSet.of(SIMPLE_MELEE)),
	WARLOCK(EnumSet.of(SIMPLE_MELEE)),
	WIZARD(EnumSet.of(SIMPLE_MELEE));

	@Getter
	private final EnumSet<WeaponCategory> weaponProficiencies;
}
