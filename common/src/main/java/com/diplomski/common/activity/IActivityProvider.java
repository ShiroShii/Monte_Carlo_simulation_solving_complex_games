package com.diplomski.common.activity;

import java.util.Optional;

import com.diplomski.common.board.BoardState;

public interface IActivityProvider {
	public Optional<Activity> getActivity(String initiatorId, String targetId, BoardState initialBoardState, IResource resource);

	public Optional<Activity> getActivity(String initiatorId, String targetId, BoardState initialBoardState);
}
