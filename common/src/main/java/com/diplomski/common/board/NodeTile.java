package com.diplomski.common.board;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NodeTile implements ITile {
	private UUID id;
	private HashSet<NodeTile> reachableTiles;
	private Optional<TerrainFeature> terrainType;
}
