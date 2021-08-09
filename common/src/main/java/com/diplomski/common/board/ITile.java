package com.diplomski.common.board;

import java.util.HashSet;
import java.util.UUID;

//Possible implementation: SquareTile, HexTile, NodeTile
public interface ITile {
	public UUID getId();
	public HashSet<UUID> getReachableTiles();
	public TerrainFeature getTerrainType();
}
