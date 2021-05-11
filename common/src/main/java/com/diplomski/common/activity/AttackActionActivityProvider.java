package com.diplomski.common.activity;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.targeting.ITargetProvider;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttackActionActivityProvider implements IActivityProvider {
	private final ITargetProvider targetProvider;
	private final IAttackRollOutcomeProvider attackRollOutcomeProvider;
	private final IDamageProvider damageProvider;
	private final IWeaponProvider weaponProvider;

	@Override
	public Activity getActivity(int initiatorIndex, BoardState initialBoardState) {
		int targetCharacterIndex = targetProvider.getTargetCharacterIndex(initiatorIndex, initialBoardState);
		CharacterState initiator = initialBoardState.getCharacterStates().get(initiatorIndex);
		CharacterState target = initialBoardState.getCharacterStates().get(targetCharacterIndex);
		Weapon weapon = weaponProvider.getWeapon(initiator);

		int damage;
		BoardState finalBoardState;

		AttackRollOutcome attackRoleOutcome = attackRollOutcomeProvider.getAttackOutcome(weapon, initiator, target);

		switch (attackRoleOutcome) {
		case HIT: {
			damage = damageProvider.getDamage(weapon, initiator, target);
			finalBoardState = initialBoardState.toBuilder().build();
			finalBoardState.getCharacterStates().get(targetCharacterIndex).takeDamage(damage);
			break;
		}
		case CRITICAL_HIT: {
			damage = damageProvider.getDamage(weapon, initiator, target) * 2;
			finalBoardState = initialBoardState.toBuilder().build();
			finalBoardState.getCharacterStates().get(targetCharacterIndex).takeDamage(damage);
			break;
		}
		default: {
			damage = 0;
			finalBoardState = initialBoardState;
			break;
		}
		}

		return AttackActionActivity.builder().initiatingCharacterIndex(initiatorIndex)
				.targetCharacterIndex(targetCharacterIndex).initialBoardState(initialBoardState)
				.finalBoardState(finalBoardState).damage(damage).build();
	}

}
