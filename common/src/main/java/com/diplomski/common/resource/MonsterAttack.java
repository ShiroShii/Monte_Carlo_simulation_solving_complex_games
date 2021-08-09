package com.diplomski.common.resource;

import java.util.Optional;

import com.diplomski.common.damage.DamageRoll;
import com.diplomski.common.damage.DamageType;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class MonsterAttack implements IResource{
	@NonNull private final String name;
	private final  CombatStyle combatStyle;
	private final int attackRollModifier;
	@NonNull private final DamageType damageType;
	@NonNull private final DamageRoll damageRoll;
	@NonNull private final Optional<Integer> meleeRange;
	@NonNull private final Optional<Integer> normalRangedRange;
	@NonNull private final Optional<Integer> longRangedRange;
	
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
