package com.diplomski.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.NodeTileDbModel;

public interface NodeTileRepository extends CrudRepository<NodeTileDbModel, Integer> {
	public Optional<NodeTileDbModel> findById(UUID id);
	public void deleteById(UUID id);
}
