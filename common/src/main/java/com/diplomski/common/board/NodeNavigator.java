package com.diplomski.common.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class NodeNavigator implements INavigator {
	@Override
	public int getMovementCost(List<ITile> path) {
		return costOfPath(path);
	}

	@Override
	public Optional<List<ITile>> getCheapestUnobstructedPath(
			UUID initialTileId,
			UUID targetTileId,
			BoardState boardState) {
		NodeTile initialTile = ((NodeBoard) boardState.getBoard()).getTiles().get(initialTileId);
		NodeTile targetTile = ((NodeBoard) boardState.getBoard()).getTiles().get(targetTileId);
		List<HashMap<UUID, ITile>> path = getPaths(new HashMap<>(), initialTile, targetTile, boardState);

		if (path.isEmpty()) {
			return Optional.empty();
		}
		List<ITile> cheapestPath = path.stream()
				.sorted(
						(path1, path2) -> costOfPath(path1)
								.compareTo(costOfPath(path2)))
				.findFirst()
				.get()
				.values()
				.stream()
				.map(tile -> (ITile) tile)
				.collect(Collectors.toCollection(ArrayList::new));

		return Optional.of(cheapestPath);
	}

	private Integer costOfPath(HashMap<UUID, ITile> path) {
		return costOfPath(path.values());
	}

	private Integer costOfPath(Collection<ITile> path) {
		return path
				.stream()
				.map(x -> x.getTerrainType().getMovementDifficulty().getMovementCost())
				.reduce(0, Integer::sum);
	}

	private List<HashMap<UUID, ITile>> getPaths(
			HashMap<UUID, ITile> path,
			NodeTile currentTile,
			NodeTile targetTile,
			BoardState boardState) {
		List<HashMap<UUID, ITile>> completePaths = new ArrayList<>();
		for (UUID reachableTileId : currentTile.getReachableTiles()) {
			if (path.containsKey(reachableTileId)) {
				// Cycle
				continue;
			}

			HashMap<UUID, ITile> potentialPath = deepCopy(path);
			NodeTile reachableTile = ((NodeBoard) boardState.getBoard()).getTiles().get(reachableTileId);

			if (reachableTileId.equals(targetTile.getId())) {
				// Complete Path
				completePaths.add(potentialPath);
				continue;
			}
			potentialPath.put(reachableTileId, reachableTile);
			completePaths.addAll(getPaths(potentialPath, reachableTile, targetTile, boardState));
		}

		return completePaths;
	}

	private HashMap<UUID, ITile> deepCopy(HashMap<UUID, ITile> original) {
		HashMap<UUID, ITile> copy = new HashMap<>();
		for (Map.Entry<UUID, ITile> entry : original.entrySet()) {
			copy.put(entry.getKey(), entry.getValue());
		}
		return copy;
	}
}
