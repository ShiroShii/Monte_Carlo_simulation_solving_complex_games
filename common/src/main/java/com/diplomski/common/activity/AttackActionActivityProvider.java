package com.diplomski.common.activity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.board.ITile;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.damage.IDamageProvider;
import com.diplomski.common.resource.IResource;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttackActionActivityProvider extends AbstractActivityProvider {
	private final IAttackRollOutcomeProvider attackRollOutcomeProvider;
	private final IDamageProvider damageProvider;

	@Override
	public Optional<Activity> getActivity(
			UUID initiatorId,
			UUID targetId,
			BoardState initialBoardState,
			List<ITile> path,
			int distance,
			double rangeMultiplier,
			IResource resource) {
		IBattleCharacterState initiator = initialBoardState.getCharacterStates().get(initiatorId);
		IBattleCharacterState target = initialBoardState.getCharacterStates().get(targetId);

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

		return Optional.of(AttackActionActivity.builder().initiatorId(initiatorId).targetId(targetId)
				.initialBoardState(initialBoardState).finalBoardState(finalBoardState).damage(damage).build());
	}

}
