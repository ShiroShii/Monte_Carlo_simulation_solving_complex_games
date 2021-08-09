package com.diplomski.common.board;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface INavigator {
	/**
	 * @return Speed cost of moving from initialTile to targetTile;
	 */
	public int getMovementCost(List<ITile> path);
	
	/**
	 * @return List of tiles between the initialTile and targetTile
	 */
	public Optional<List<ITile>> getCheapestUnobstructedPath(UUID initialTile, UUID targetTile, BoardState boardState);

	/**
	 * @param moveReferencingTarget Set to 0 to move to the target tile. <br/>
	 *                              Set to action range as positive number to get
	 *                              target into range. <br/>
	 *                              Set to required distance as negative number to
	 *                              move away. <br/>
	 */
	public ITile moveReferencingTarget(ITile initialTile, ITile targetTile, int distanceFromTarget);
}
