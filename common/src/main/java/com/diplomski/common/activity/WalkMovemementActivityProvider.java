package com.diplomski.common.activity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.diplomski.common.board.BoardState;
import com.diplomski.common.board.ITile;
import com.diplomski.common.character.IBattleCharacterState;
import com.diplomski.common.resource.IResource;

public class WalkMovemementActivityProvider extends AbstractActivityProvider {
	@Override
	public Optional<Activity> getActivity(
			UUID initiatorId,
			UUID targetId,
			BoardState initialBoardState,
			List<ITile> path,
			int distance,
			double rangeMultiplier,
			IResource resource) {
		IBattleCharacterState initiator = initialBoardState.getCharacterStates().get(initiatorId);

		int usedWalkingSpeed = 0;
		int nextTileCost = path.get(0).getTerrainType().getMovementDificulty().getMovementCost();
		UUID finalTileId = null;

		while (!path.isEmpty() && usedWalkingSpeed + nextTileCost < initiator.getWalkingSpeed()) {
			finalTileId = path.get(0).getId();
			usedWalkingSpeed += nextTileCost;
			path.remove(0);
			if (!path.isEmpty()) {
				nextTileCost = path.get(0).getTerrainType().getMovementDificulty().getMovementCost();
			}
		}
		if (usedWalkingSpeed > 0 && finalTileId != null) {
			BoardState finalBoardState = initialBoardState.toBuilder().build();
			finalBoardState.getCharacterStates().get(initiatorId).setTileId(finalTileId);
			finalBoardState.getCharacterStates().get(initiatorId).setUsedWalkingSpeed(usedWalkingSpeed);

			return Optional.of(WalkMovementActivity.builder().initiatorId(initiatorId)
					.initialBoardState(initialBoardState).finalBoardState(finalBoardState)
					.initialTileId(initiator.getTileId()).finalTileId(finalTileId).build());
		}
		return Optional.empty();
	}
}
