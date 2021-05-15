package com.diplomski.common.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.diplomski.common.activity.Activity;
import com.diplomski.common.activity.IActivityProvider;
import com.diplomski.common.activity.IResource;
import com.diplomski.common.activity.WeaponStyle;
import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.BattleCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
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
		BattleCharacterState initiator = initialBoardState.getCharacterStates().get(initiatorId);

		IResource resource = switch (playStyle) {
			default -> initiator.getWeapons().stream().filter(x -> x.getWeaponStyle().equals(WeaponStyle.MELEE))
					.findFirst().get();

		};

		Optional<String> targetIdOptional = targetProvider.getTargetId(initiatorId, targetParty, currentBoardState);

		if (targetIdOptional.isPresent()) {

			Activity movementActivity = movementProvider
					.getActivity(initiatorId, targetIdOptional.get(), currentBoardState);
			currentBoardState = movementActivity.getFinalBoardState();
			activities.add(movementActivity);

			Activity actionActivity = actionProvider
					.getActivity(initiatorId, targetIdOptional.get(), currentBoardState, resource);
			currentBoardState = actionActivity.getFinalBoardState();
			activities.add(actionActivity);
		}

		return Turn.builder().initiatorId(initiatorId).initialBoardState(initialBoardState)
				.finalBoardState(currentBoardState).activities(activities).build();
	}
}
