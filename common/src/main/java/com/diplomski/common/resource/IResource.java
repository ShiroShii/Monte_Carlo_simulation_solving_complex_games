package com.diplomski.common.resource;

import java.util.Optional;

import com.diplomski.common.damage.DamageRoll;
import com.diplomski.common.damage.DamageType;

public interface IResource {
	public CombatStyle getCombatStyle();
	public DamageType getDamageType();
	public DamageRoll getDamageRoll();
	public int getRange();
	public Optional<Integer> getLongRange();

	public default double rangeMultiplier(int distance) {

		if (distance <= getRange()) {
			return 1D;
		}

		if (getLongRange().isPresent() && distance <= getLongRange().get()) {
			return 0.5D;
		}

		return 0D;
	}
}
