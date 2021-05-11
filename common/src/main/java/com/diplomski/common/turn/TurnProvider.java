package com.diplomski.common.turn;

import java.util.Arrays;
import java.util.List;

import com.diplomski.common.activity.Activity;
import com.diplomski.common.activity.IActivityProvider;
import com.diplomski.common.board.BoardState;

import lombok.AllArgsConstructor;

/**
 * Provides turn with one movement activity, then one action activity.
 */
@AllArgsConstructor
public class TurnProvider implements ITurnProvider {
	private final IActivityProvider movementProvider;
	private final IActivityProvider actionProvider;

	@Override
	public Turn getTurn(int initiatorIndex, BoardState initialBoardState) {
		BoardState currentBoardState = initialBoardState;

		Activity movementActivity = movementProvider.getActivity(initiatorIndex, currentBoardState);
		currentBoardState = movementActivity.getFinalBoardState();

		Activity actionActivity = actionProvider.getActivity(initiatorIndex, currentBoardState);
		currentBoardState = actionActivity.getFinalBoardState();

		List<Activity> activities = Arrays.asList(movementActivity, actionActivity);

		return Turn.builder().initiatorIndex(initiatorIndex).initialBoardState(initialBoardState)
				.finalBoardState(currentBoardState).activities(activities).build();
	}
}
