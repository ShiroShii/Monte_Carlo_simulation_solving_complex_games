package com.diplomski.backend.translator;

import java.util.stream.Collectors;

import com.diplomski.backend.contract.NodeTileResponse;
import com.diplomski.backend.dal.NodeTileDbModel;

public class NodeBoardTranslator {
	public static NodeTileResponse translate(NodeTileDbModel input) {
		return NodeTileResponse.builder().id(input.getId()).terrainFeature(input.getTerrainFeature())
				.reachableTiles(input.getReachableNodes().stream().map(x -> x.getId()).collect(Collectors.toList()))
				.build();
	}
}
