package com.diplomski.common.activity;

import com.diplomski.common.board.BoardState;

public interface IActivityProvider {
	public Activity getActivity(String initiatorId, String targetId, BoardState initialBoardState, IResource resource);

	public Activity getActivity(String initiatorId, String targetId, BoardState initialBoardState);
}
