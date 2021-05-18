package com.diplomski.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.BoardStateDbModel;

public interface BoardStateRepository extends CrudRepository<BoardStateDbModel, Integer> {
}
