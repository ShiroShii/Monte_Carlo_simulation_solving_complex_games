package com.diplomski.common.board;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TerrainFeature {
	private ITile tile;
	private TerrainFeatureType terrainFeatureType;
}
