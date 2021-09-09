package com.diplomski.backend.contract;

import java.util.List;

import com.diplomski.common.board.TerrainFeature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeTileCreateRequest {
	private int x;
	private int y;
	
	private List<Integer> reachableTiles;
	private List<NodeTileCover> tileCover;
	
	private TerrainFeature terrainFeature;
	
	private List<PlayerCharacterStateCreateRequest> playerCharacterStates;
	private List<MonsterStateCreateRequest> monsterStates;
}
