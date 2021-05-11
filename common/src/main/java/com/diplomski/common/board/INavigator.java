package com.diplomski.common.board;

public interface INavigator {
	/**
	 * @return Number of tiles between the given two tiles.
	 */
	public int tileDistance(Tile tile1, Tile tile2);

	/**
	 * @return Speed cost of moving from initialTile to targetTile;
	 */
	public int movementSpeedCost(Tile initialTile, Tile targetTile, BoardState boardState);

	/**
	 * @param distanceFromTarget 
	 * Set to 0 to move to the target tile. <br/>
	 * Set to action range as positive number to get target into range. <br/>
	 * Set to required distance as negative number to move away. <br/>
	 */
	public Tile moveTowardsTargetTile(Tile initialTile, Tile targetTile, int distanceFromTarget);
}
