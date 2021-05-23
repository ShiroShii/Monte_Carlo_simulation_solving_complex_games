package com.diplomski.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.diplomski.backend.dal.NodeBoardDbModel;
import com.diplomski.backend.repository.RepositoryProvider;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BoardService {
	@Autowired
	private final RepositoryProvider repositoryProvider;

	public Optional<NodeBoardDbModel> getBoard(UUID id) {
		return repositoryProvider.getNodeBoardRepository().findById(id);
	}
	
	public List<NodeBoardDbModel> getAllBoards(){
		return repositoryProvider.getNodeBoardRepository().findAll();
	}

	public void deleteBoard(UUID id) {
		repositoryProvider.getNodeBoardRepository().deleteById(id);
	}
}
