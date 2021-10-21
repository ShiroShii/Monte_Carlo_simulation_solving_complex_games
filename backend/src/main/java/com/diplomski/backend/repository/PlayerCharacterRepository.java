package com.diplomski.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.PlayerCharacterDbModel;

public interface PlayerCharacterRepository
		extends CrudRepository<PlayerCharacterDbModel, Integer> {
	public Optional<PlayerCharacterDbModel> findById(UUID id);

	@Override
	List<PlayerCharacterDbModel> findAll();
}
