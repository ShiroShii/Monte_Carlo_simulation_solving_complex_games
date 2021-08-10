package com.diplomski.common.activity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.board.ITile;
import com.diplomski.common.resource.IResource;

public abstract class AbstractActivityProvider implements IActivityProvider {
	public abstract Optional<Activity> getActivity(
			UUID initiatorId,
			UUID targetId,
			BoardState initialBoardState,
			List<ITile> path,
			int distance,
			double rangeMultiplier,
			IResource resource);

	public Optional<Activity> getActivity(
			UUID initiatorId,
			UUID targetId,
			BoardState initialBoardState,
			List<ITile> path,
			int distance,
			double rangeMultiplier) {
		return getActivity(initiatorId, targetId, initialBoardState, path, distance, rangeMultiplier, null);
	}
}
