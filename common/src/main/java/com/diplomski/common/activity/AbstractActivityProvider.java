package com.diplomski.common.activity;

import java.util.Optional;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.resource.IResource;

public abstract class AbstractActivityProvider implements IActivityProvider {
	public abstract Optional<Activity> getActivity(
			String initiatorId,
			String targetId,
			BoardState initialBoardState,
			IResource resource);

	public Optional<Activity> getActivity(String initiatorId, String targetId, BoardState initialBoardState) {
		return getActivity(initiatorId, targetId, initialBoardState, null);
	}
}
