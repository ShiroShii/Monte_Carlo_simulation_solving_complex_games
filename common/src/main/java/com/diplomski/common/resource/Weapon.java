package com.diplomski.common.resource;

import static com.diplomski.common.damage.DamageType.BLUDGEONING;
import static com.diplomski.common.damage.DamageType.PIERCING;
import static com.diplomski.common.dice.DiceType.D4;
import static com.diplomski.common.resource.WeaponCategory.SIMPLE_MELEE;
import static com.diplomski.common.resource.WeaponProperty.FINESSE;
import static com.diplomski.common.resource.WeaponProperty.LIGHT;
import static com.diplomski.common.resource.WeaponProperty.THROWN;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;

import com.diplomski.common.damage.DamageRoll;
import com.diplomski.common.damage.DamageType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Weapon implements IResource {
	CLUB(
			SIMPLE_MELEE,
			BLUDGEONING,
			EnumSet.of(LIGHT),
			DamageRoll.builder().dice(Arrays.asList(D4)).build(),
			Optional.of(1),
			Optional.of(4),
			Optional.of(12)),
	DAGGER(
			SIMPLE_MELEE,
			PIERCING,
			EnumSet.of(FINESSE, LIGHT, THROWN),
			DamageRoll.builder().dice(Arrays.asList(D4)).build(),
			Optional.of(1),
			Optional.of(4),
			Optional.of(12));

	@Getter
	private final WeaponCategory weaponCategory;

	@Getter
	private final DamageType damageType;

	@Getter
	private final EnumSet<WeaponProperty> properties;

	@Getter
	private final DamageRoll damageRoll;

	@Getter
	private final Optional<Integer> meleeRange;

	@Getter
	private final Optional<Integer> normalRangedRange;

	@Getter
	private final Optional<Integer> longRangedRange;

	@Override
	public CombatStyle getCombatStyle() {
		return this.weaponCategory.getStyle();
	}
	
	@Override
	public double rangeMultiplier(int distance, CombatStyle combatStyle) {
		return switch (combatStyle) {
			case MELEE -> {
				if(meleeRange.isPresent() && distance<=meleeRange.get()) {
					yield 1D;
				}
				else {
					yield 0D;
				}
			}
			case RANGED -> {
				if(normalRangedRange.isPresent() && distance<=normalRangedRange.get()) {
					yield 1D;
				}
				
				if(longRangedRange.isPresent() && distance<=longRangedRange.get()) {
					yield 0.5D;
				}
				
				yield 0D;
				
			}
		};
	}
}
