package com.diplomski.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.PlayerCharacterStateDbModel;

public interface CharacterStateRepository extends CrudRepository<PlayerCharacterStateDbModel, Integer> {
	public Optional<PlayerCharacterStateDbModel> findById(UUID id);
	public void deleteById(UUID id);
}
