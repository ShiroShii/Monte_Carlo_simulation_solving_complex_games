package com.diplomski.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.diplomski.backend.dal.BoardStateDbModel;
import com.diplomski.backend.repository.RepositoryProvider;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BoardStateService {
	@Autowired
	private final RepositoryProvider repositoryProvider;
	
	public Optional<BoardStateDbModel> getBoardState(UUID id) {
		return repositoryProvider.getBoardStateRepository().findById(id);
	}
	
	public void deleteBoardState(UUID id) {
		repositoryProvider.getBoardStateRepository().deleteById(id);
	}
	
	public List<BoardStateDbModel> getAllBoards(){
		return repositoryProvider.getBoardStateRepository().findAll();
	}
}
