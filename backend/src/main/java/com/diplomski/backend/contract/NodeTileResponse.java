package com.diplomski.backend.contract;

import java.util.List;
import java.util.UUID;

import com.diplomski.common.board.TerrainFeature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeTileResponse {
	private UUID id;
	private List<UUID> reachableTiles;
	private TerrainFeature terrainFeature;
}
