package com.diplomski.common.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.diplomski.common.activity.Activity;
import com.diplomski.common.activity.IActivityProvider;
import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.resource.IResource;
import com.diplomski.common.resource.WeaponCategory;
import com.diplomski.common.targeting.ITargetProvider;

import lombok.AllArgsConstructor;

/**
 * Provides turn with one movement activity, then one action activity.
 */
@AllArgsConstructor
public class TurnProvider implements ITurnProvider {
	private final String initiatorId;
	private final Party targetParty;
	private final ITargetProvider targetProvider;
	private final IActivityProvider movementProvider;
	private final IActivityProvider actionProvider;
	private final PlayStyle playStyle;

	@Override
	public Turn getTurn(BoardState initialBoardState) {
		BoardState currentBoardState = initialBoardState;
		List<Activity> activities = new ArrayList<>();
		IBattleCharacterState initiator = initialBoardState.getCharacterStates().get(initiatorId);

		IResource resource = switch (playStyle) {
			default -> initiator.getWeapons().stream()
					.filter(x -> x.getWeaponCategory().equals(WeaponCategory.SIMPLE_MELEE)).findFirst().get();

		};

		Optional<String> targetIdOptional = targetProvider.getTargetId(initiatorId, targetParty, currentBoardState);

		if (targetIdOptional.isPresent()) {
			Optional<Activity> movementActivity = movementProvider
					.getActivity(initiatorId, targetIdOptional.get(), currentBoardState);
			if (movementActivity.isPresent()) {
				currentBoardState = movementActivity.get().getFinalBoardState();
				activities.add(movementActivity.get());
			}

			Optional<Activity> actionActivity = actionProvider
					.getActivity(initiatorId, targetIdOptional.get(), currentBoardState, resource);
			if (actionActivity.isPresent()) {
				currentBoardState = actionActivity.get().getFinalBoardState();
				activities.add(actionActivity.get());
			}
		}

		return Turn.builder().initiatorId(initiatorId).initialBoardState(initialBoardState)
				.finalBoardState(currentBoardState).activities(activities).build();
	}
}
