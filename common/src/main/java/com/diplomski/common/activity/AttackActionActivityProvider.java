package com.diplomski.common.activity;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.BattleCharacterState;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttackActionActivityProvider extends AbstractActivityProvider {
	private final IAttackRollOutcomeProvider attackRollOutcomeProvider;
	private final IDamageProvider damageProvider;

	@Override
	public Activity getActivity(String initiatorId, String targetId, BoardState initialBoardState, IResource resource) {
		BattleCharacterState initiator = initialBoardState.getCharacterStates().get(initiatorId);
		BattleCharacterState target = initialBoardState.getCharacterStates().get(targetId);

		AttackRollOutcome attackRoleOutcome = attackRollOutcomeProvider.getAttackOutcome(resource, initiator, target);

		int damage = switch (attackRoleOutcome) {
			case HIT -> damageProvider.getDamage(resource, initiator, target);
			case CRITICAL_HIT -> damageProvider.getDamage(resource, initiator, target) * 2;
			default -> 0;
		};

		// TODO: remove thrown item

		BoardState finalBoardState = switch (attackRoleOutcome) {
			case HIT, CRITICAL_HIT -> {
				BoardState temporaryBoardState = initialBoardState.toBuilder().build();
				temporaryBoardState.getCharacterStates().get(targetId).takeDamage(damage);
				yield temporaryBoardState;
			}
			default -> initialBoardState;
		};

		return AttackActionActivity.builder().initiatorId(initiatorId).targetId(targetId)
				.initialBoardState(initialBoardState).finalBoardState(finalBoardState).damage(damage).build();
	}

}
