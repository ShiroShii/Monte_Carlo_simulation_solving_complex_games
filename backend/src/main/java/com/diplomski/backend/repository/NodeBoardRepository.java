package com.diplomski.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.NodeBoardDbModel;

public interface NodeBoardRepository extends CrudRepository<NodeBoardDbModel, Integer> {
	public Optional<NodeBoardDbModel> findById(UUID id);
	public void deleteById(UUID id);
    @Override
    List<NodeBoardDbModel> findAll();
}
