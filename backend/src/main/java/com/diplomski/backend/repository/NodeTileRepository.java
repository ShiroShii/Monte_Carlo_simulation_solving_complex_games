package com.diplomski.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.NodeTileDbModel;

public interface NodeTileRepository extends CrudRepository<NodeTileDbModel, Integer> {
}
