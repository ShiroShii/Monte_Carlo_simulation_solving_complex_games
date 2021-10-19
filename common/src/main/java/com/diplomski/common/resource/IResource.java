package com.diplomski.common.resource;

import java.util.List;
import java.util.Optional;

import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.damage.DamageRoll;

public interface IResource {
	public CombatStyle getCombatStyle();
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
	
	public static Optional<IResource> getResource(List<IResource> resources, PlayStyle playStyle) {
		return switch (playStyle) {
			case MELEE_DAMAGE -> resources.stream()
					.filter(x -> x.getCombatStyle().equals(CombatStyle.MELEE)).findAny();
			case RANGED_DAMAGE -> resources.stream()
					.filter(x -> x.getCombatStyle().equals(CombatStyle.RANGED)).findAny();
			default -> Optional.empty();
		};
	}
}
