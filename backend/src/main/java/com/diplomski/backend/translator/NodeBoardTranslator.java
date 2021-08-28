package com.diplomski.backend.translator;

import java.util.List;
import java.util.stream.Collectors;

import com.diplomski.backend.contract.NodeBoardContract;
import com.diplomski.backend.contract.NodeTileResponse;
import com.diplomski.backend.dal.NodeBoardDbModel;
import com.diplomski.backend.dal.NodeTileDbModel;

public class NodeBoardTranslator {
	public static NodeTileResponse translate(NodeTileDbModel input) {
		return NodeTileResponse.builder().id(input.getId()).terrainFeature(input.getTerrainFeature())
				.reachableTiles(input.getReachableNodes().stream().map(x -> x.getId()).collect(Collectors.toList()))
				.build();
	}

	public static NodeBoardContract translate(NodeBoardDbModel input) {
		return NodeBoardContract.builder().id(input.getId()).name(input.getName())
				.nodes(input.getNodeTiles().stream().map(x -> translate(x)).toList()).build();
	}
	
	public static List<NodeBoardContract> translate(List<NodeBoardDbModel> input) {
		return input.stream().map(x -> translate(x)).toList();
	}
}
