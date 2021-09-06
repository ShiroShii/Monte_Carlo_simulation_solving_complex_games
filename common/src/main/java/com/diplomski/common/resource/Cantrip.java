package com.diplomski.common.resource;

import static com.diplomski.common.damage.DamageType.FIRE;
import static com.diplomski.common.damage.DamageType.LIGHTNING;
import static com.diplomski.common.dice.DiceType.D10;
import static com.diplomski.common.dice.DiceType.D8;
import static com.diplomski.common.resource.CombatStyle.MELEE;
import static com.diplomski.common.resource.CombatStyle.RANGED;

import java.util.Arrays;
import java.util.Optional;

import com.diplomski.common.damage.DamageRoll;
import com.diplomski.common.damage.DamageType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Cantrip implements IResource {
	SHOCKING_GRASP(
			MELEE,
			LIGHTNING,
			DamageRoll.builder().dice(Arrays.asList(D8)).build(),
			Optional.of(1),
			Optional.empty(),
			Optional.empty()),
	FIRE_BOLT(
			RANGED,
			FIRE,
			DamageRoll.builder().dice(Arrays.asList(D10)).build(),
			Optional.empty(),
			Optional.of(24),
			Optional.empty());

	@Getter
	private final CombatStyle combatStyle;

	@Getter
	private final DamageType damageType;

	@Getter
	// TODO: spell damage increases with level
	private final DamageRoll damageRoll;

	@Getter
	private final Optional<Integer> meleeRange;

	@Getter
	private final Optional<Integer> normalRangedRange;

	@Getter
	private final Optional<Integer> longRangedRange;

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
