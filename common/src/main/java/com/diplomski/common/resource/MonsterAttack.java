package com.diplomski.common.resource;

import java.util.Optional;

import com.diplomski.common.damage.DamageRoll;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class MonsterAttack implements IResource {
	@NonNull
	private final String name;
	private final CombatStyle combatStyle;
	private final int attackRollModifier;
	@NonNull
	private final DamageRoll damageRoll;
	private final int range;
	@NonNull
	private final Optional<Integer> longRange;
}
