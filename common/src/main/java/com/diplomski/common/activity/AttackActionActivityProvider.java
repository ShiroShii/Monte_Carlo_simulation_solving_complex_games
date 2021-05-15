package com.diplomski.common.activity;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.BattleCharacterState;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttackActionActivityProvider extends AbstractActivityProvider {
	private final IAttackRollOutcomeProvider attackRollOutcomeProvider;
	private final IDamageProvider damageProvider;

	@Override
	public Activity getActivity(String initiatorId, String targetId, BoardState initialBoardState, IResource weapon) {
		BattleCharacterState initiator = initialBoardState.getCharacterStates().get(initiatorId);
		BattleCharacterState target = initialBoardState.getCharacterStates().get(targetId);

		int damage;
		BoardState finalBoardState;

		AttackRollOutcome attackRoleOutcome = attackRollOutcomeProvider.getAttackOutcome((Weapon) weapon, initiator,
				target);

		switch (attackRoleOutcome) {
		case HIT: {
			damage = damageProvider.getDamage((Weapon) weapon, initiator, target);
			finalBoardState = initialBoardState.toBuilder().build();
			finalBoardState.getCharacterStates().get(targetId).takeDamage(damage);
			break;
		}
		case CRITICAL_HIT: {
			damage = damageProvider.getDamage((Weapon) weapon, initiator, target) * 2;
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

		return AttackActionActivity.builder().initiatorId(initiatorId).targetId(targetId)
				.initialBoardState(initialBoardState).finalBoardState(finalBoardState).damage(damage).build();
	}

}
