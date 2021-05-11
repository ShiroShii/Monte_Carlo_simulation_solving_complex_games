package com.diplomski.common.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.diplomski.common.activity.Activity;
import com.diplomski.common.activity.IActivityProvider;
import com.diplomski.common.board.BoardState;
import com.diplomski.common.character.Party;
import com.diplomski.common.targeting.ITargetProvider;

import lombok.AllArgsConstructor;

/**
 * Provides turn with one movement activity, then one action activity.
 */
@AllArgsConstructor
public class TurnProvider implements ITurnProvider {
	private final Party targetParty;
	private final ITargetProvider targetProvider;
	private final IActivityProvider movementProvider;
	private final IActivityProvider actionProvider;

	@Override
	public Turn getTurn(int initiatorIndex, BoardState initialBoardState) {
		BoardState currentBoardState = initialBoardState;
		List<Activity> activities = new ArrayList<>();
		
		Optional<Integer> targetIndexOptional = targetProvider.getTargetCharacterIndex(initiatorIndex, targetParty,
				currentBoardState);

		if (targetIndexOptional.isPresent()) {

			Activity movementActivity = movementProvider.getActivity(initiatorIndex, targetIndexOptional.get(),
					currentBoardState);
			currentBoardState = movementActivity.getFinalBoardState();
			activities.add(movementActivity);

			Activity actionActivity = actionProvider.getActivity(initiatorIndex, targetIndexOptional.get(),
					currentBoardState);
			currentBoardState = actionActivity.getFinalBoardState();
			activities.add(actionActivity);
		}

		return Turn.builder().initiatorIndex(initiatorIndex).initialBoardState(initialBoardState)
				.finalBoardState(currentBoardState).activities(activities).build();
	}
}
