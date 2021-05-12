package com.diplomski.common.activity;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.CharacterState;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttackActionActivityProvider implements IActivityProvider {
	private final IAttackRollOutcomeProvider attackRollOutcomeProvider;
	private final IDamageProvider damageProvider;
	private final IWeaponProvider weaponProvider;

	@Override
	public Activity getActivity(String initiatorId, String targetId, BoardState initialBoardState) {
		CharacterState initiator = initialBoardState.getCharacterStates().get(initiatorId);
		CharacterState target = initialBoardState.getCharacterStates().get(targetId);
		Weapon weapon = weaponProvider.getWeapon(initiator);

		int damage;
		BoardState finalBoardState;

		AttackRollOutcome attackRoleOutcome = attackRollOutcomeProvider.getAttackOutcome(weapon, initiator, target);

		switch (attackRoleOutcome) {
		case HIT: {
			damage = damageProvider.getDamage(weapon, initiator, target);
			finalBoardState = initialBoardState.toBuilder().build();
			finalBoardState.getCharacterStates().get(targetId).takeDamage(damage);
			break;
		}
		case CRITICAL_HIT: {
			damage = damageProvider.getDamage(weapon, initiator, target) * 2;
			finalBoardState = initialBoardState.toBuilder().build();
			finalBoardState.getCharacterStates().get(targetId).takeDamage(damage);
			break;
		}
		default: {
			damage = 0;
			finalBoardState = initialBoardState;
			break;
		}
		}

		return AttackActionActivity.builder().initiatorId(initiatorId)
				.targetId(targetId).initialBoardState(initialBoardState)
				.finalBoardState(finalBoardState).damage(damage).build();
	}

}
