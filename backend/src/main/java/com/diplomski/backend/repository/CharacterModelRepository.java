package com.diplomski.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.CharacterModelDbModel;

public interface CharacterModelRepository extends CrudRepository<CharacterModelDbModel, Integer> {
	public Optional<CharacterModelDbModel> findById(UUID id);
}
