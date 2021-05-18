package com.diplomski.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.CharacterStateDbModel;

public interface CharacterStateRepository extends CrudRepository<CharacterStateDbModel, Integer> {
}
