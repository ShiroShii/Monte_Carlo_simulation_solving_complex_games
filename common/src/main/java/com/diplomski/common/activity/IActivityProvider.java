package com.diplomski.common.activity;

import java.util.Optional;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.resource.IResource;

public interface IActivityProvider {
	public Optional<Activity> getActivity(String initiatorId, String targetId, BoardState initialBoardState, IResource resource);

	public Optional<Activity> getActivity(String initiatorId, String targetId, BoardState initialBoardState);
}
