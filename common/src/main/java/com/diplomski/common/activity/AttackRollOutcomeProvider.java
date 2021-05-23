package com.diplomski.common.activity;

import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.damage.IArmorClassProvider;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.resource.IResource;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AttackRollOutcomeProvider implements IAttackRollOutcomeProvider {
	private final @NonNull IAttackRollModifierProvider attackRollModifierProvider;
	private final @NonNull IArmorClassProvider armorClassProvider;
	private final @NonNull IDiceFactory diceFactory;

	@Override
	public AttackRollOutcome getAttackOutcome(
			@NonNull IResource resource,
			@NonNull BattleCharacterState initiator,
			@NonNull BattleCharacterState target) {
		// TODO: Check for disadvantage
		int d20Role = diceFactory.getD20().getRoll();

		if (d20Role == 1) {
			return AttackRollOutcome.FUMBLE;
		}

		if (d20Role == 20) {
			return AttackRollOutcome.CRITICAL_HIT;
		}

		boolean isHit = d20Role + attackRollModifierProvider.getAttackRollModifier(resource, initiator)
				>= armorClassProvider.getArmorClass(target);

		if (isHit) {
			return AttackRollOutcome.HIT;
		} else {
			return AttackRollOutcome.MISS;
		}
	}

}
