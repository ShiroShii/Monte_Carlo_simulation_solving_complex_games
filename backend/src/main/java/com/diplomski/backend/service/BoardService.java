package com.diplomski.backend.service;

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
	private final RepositoryProvider unitOfWork;
	
	public Optional<NodeBoardDbModel> getBoard(UUID id) {
		return unitOfWork.getNodeBoardRepository().findById(id);
	}
}
