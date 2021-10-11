package com.diplomski.common.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class NodeNavigator implements INavigator {
	@Override
	public int getMovementCost(List<ITile> path) {
		return costOfPath(path);
	}

	@Override
	public Optional<List<ITile>> getCheapestUnobstructedPath(UUID initialTileId, UUID targetTileId, BoardState boardState) {
		NodeTile initialTile = ((NodeBoard)boardState.getBoard()).getTiles().get(initialTileId);
		NodeTile targetTile = ((NodeBoard)boardState.getBoard()).getTiles().get(targetTileId);
		List<HashMap<UUID, NodeTile>> path = getPath(new HashMap<>(), initialTile, targetTile, boardState);

		if (path.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(new ArrayList<>(path.stream().sorted((path1, path2) -> {
			return costOfPath(new ArrayList<>(path1.values())) >= costOfPath(new ArrayList<>(path2.values())) ? 1 : -1;
		}).findFirst().get().values()));
	}

	private int costOfPath(List<ITile> path) {
		return path.stream().map(x -> x.getTerrainType().getMovementDifficulty().getMovementCost())
				.reduce(0, Integer::sum);
	}

	private List<HashMap<UUID, NodeTile>> getPath(
			HashMap<UUID, NodeTile> path,
			NodeTile currentTile,
			NodeTile targetTile,
			BoardState boardState) {
		// TODO: path obstruction in getPath
		List<HashMap<UUID, NodeTile>> completePaths = new ArrayList<>();
		for (UUID reachableTileId : currentTile.getReachableTiles()) {
			if (path.containsKey(reachableTileId)) {
				// Cycle
				continue;
			}

			HashMap<UUID, NodeTile> potentialPath = deepCopy(path);
			NodeTile reachableTile = ((NodeBoard) boardState.getBoard()).getTiles().get(reachableTileId);

			if (reachableTileId.equals(targetTile.getId())) {
				// Complete Path
				completePaths.add(potentialPath);
				continue;
			}
			potentialPath.put(reachableTileId, reachableTile);
			completePaths.addAll(getPath(potentialPath, reachableTile, targetTile, boardState));
		}

		return completePaths;
	}

	private HashMap<UUID, NodeTile> deepCopy(HashMap<UUID, NodeTile> original) {
		HashMap<UUID, NodeTile> copy = new HashMap<>();
		for (Map.Entry<UUID, NodeTile> entry : original.entrySet()) {
			copy.put(entry.getKey(), entry.getValue());
		}
		return copy;
	}
}
