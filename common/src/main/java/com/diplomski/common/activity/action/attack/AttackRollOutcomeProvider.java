package com.diplomski.common.activity.action.attack;

import static com.diplomski.common.dice.DiceType.D20;

import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.resource.IResource;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AttackRollOutcomeProvider implements IAttackRollOutcomeProvider {
	private final @NonNull IAttackRollModifierProvider attackRollModifierProvider;
	private final @NonNull IDiceFactory diceFactory;

	@Override
	public AttackRollOutcome getAttackOutcome(
			@NonNull IResource resource,
			@NonNull IBattleCharacterState initiator,
			@NonNull IBattleCharacterState target) {
		int d20Role = diceFactory.getDice(D20).getRoll();

		if (d20Role == 1) {
			return AttackRollOutcome.FUMBLE;
		}

		if (d20Role == 20) {
			return AttackRollOutcome.CRITICAL_HIT;
		}

		boolean isHit = d20Role + attackRollModifierProvider.getAttackRollModifier(resource, initiator)
				>= target.getArmorClass();

		if (isHit) {
			return AttackRollOutcome.HIT;
		} else {
			return AttackRollOutcome.MISS;
		}
	}
}
