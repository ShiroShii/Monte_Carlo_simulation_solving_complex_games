package com.diplomski.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.diplomski.backend.contract.NodeBoardCreateRequest;
import com.diplomski.backend.contract.NodeTileCreateRequest;
import com.diplomski.backend.dal.NodeBoardDbModel;
import com.diplomski.backend.dal.NodeTileDbModel;
import com.diplomski.backend.repository.NodeBoardRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BoardService {
	private NodeBoardRepository nodeBoardRepository;

	public NodeBoardDbModel saveBoard(NodeBoardCreateRequest request) {
		NodeBoardDbModel board = NodeBoardDbModel.builder().name(request.getName()).build();

		List<NodeTileDbModel> nodes = new ArrayList<>();

		for (NodeTileCreateRequest nodeRequest : request.getNodes()) {
			NodeTileDbModel node = NodeTileDbModel.builder().nodeBoard(board)
					.terrainFeature(nodeRequest.getTerrainFeature()).build();
			nodes.add(node);
		}

		for (int i = 0; i < nodes.size(); i++) {
			NodeTileDbModel node = nodes.get(i);
			List<NodeTileDbModel> reachableNodes = new ArrayList<>();
			for (int reachableIndex : request.getNodes().get(i).getReachableTiles()) {
				reachableNodes.add(nodes.get(reachableIndex));
			}
			node.setReachableNodes(reachableNodes);

			nodes.set(i, node);
		}

		board.setNodeTiles(nodes);
		return nodeBoardRepository.save(board);
	}

	public Optional<NodeBoardDbModel> getBoard(UUID id) {
		return nodeBoardRepository.findById(id);
	}
	
	public NodeBoardDbModel updateBoard(NodeBoardDbModel request) {
		return nodeBoardRepository.save(request);
	}

	public List<NodeBoardDbModel> getAll() {
		return nodeBoardRepository.findAll();
	}

	public void deleteBoard(UUID id) {
		nodeBoardRepository.deleteById(id);
	}
}
