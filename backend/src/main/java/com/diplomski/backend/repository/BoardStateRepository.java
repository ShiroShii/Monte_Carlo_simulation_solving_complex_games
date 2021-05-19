package com.diplomski.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.BoardStateDbModel;

public interface BoardStateRepository extends CrudRepository<BoardStateDbModel, Integer> {
	public Optional<BoardStateDbModel> findById(UUID id);
}
