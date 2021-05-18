package com.diplomski.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.NodeBoardDbModel;

public interface NodeBoardRepository extends CrudRepository<NodeBoardDbModel, Integer> {
}
