package com.diplomski.common.activity;

import com.diplomski.common.board.BoardState;

public abstract class AbstractActivityProvider implements IActivityProvider {
	public abstract Activity getActivity(
			String initiatorId,
			String targetId,
			BoardState initialBoardState,
			IResource resource);

	public Activity getActivity(String initiatorId, String targetId, BoardState initialBoardState) {
		return getActivity(initiatorId, targetId, initialBoardState, null);
	}
}
