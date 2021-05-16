package com.diplomski.common.board;

import java.util.Optional;

public class NodeNavigator implements INavigator {

	@Override
	public Optional<Integer> getUnobstructedTileDistance(ITile tile1, ITile tile2) {
		// TODO: implement NodeNavigator getUnobstructedTileDistance
		return Optional.of(5);
	}

	@Override
	public int getMovementCost(ITile initialTile, ITile targetTile, BoardState boardState) {
		// TODO: implement NodeNavigator getMovementCost
		return 5;
	}

	@Override
	public ITile moveTowardsTargetTile(ITile initialTile, ITile targetTile, int distanceFromTarget) {
		// TODO: implement NodeNavigator moveTowardsTargetTile
		return targetTile;
	}

}
