package com.diplomski.common.activity;

import java.util.Optional;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.resource.IResource;

public class WalkMovemementActivityProvider extends AbstractActivityProvider {
	@Override
	public Optional<Activity> getActivity(String initiatorId, String targetId, BoardState initialBoardState, IResource resource) {
		// TODO: Implement WalkMovementActivityProvider
		return Optional.empty();
	}

}
