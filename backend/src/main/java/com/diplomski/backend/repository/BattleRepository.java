package com.diplomski.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.BattleDbModel;

public interface BattleRepository extends CrudRepository<BattleDbModel, Integer> {
	public Optional<BattleDbModel> findById(UUID id);
	public void deleteById(UUID id);
    @Override
    List<BattleDbModel> findAll();
}
