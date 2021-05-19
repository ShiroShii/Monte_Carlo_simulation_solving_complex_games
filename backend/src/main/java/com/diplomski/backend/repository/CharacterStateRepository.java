package com.diplomski.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.CharacterStateDbModel;

public interface CharacterStateRepository extends CrudRepository<CharacterStateDbModel, Integer> {
	public Optional<CharacterStateDbModel> findById(UUID id);
}
