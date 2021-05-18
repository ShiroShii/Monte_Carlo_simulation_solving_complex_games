package com.diplomski.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.diplomski.backend.dal.CharacterModelDbModel;

public interface CharacterModelRepository extends CrudRepository<CharacterModelDbModel, Integer> {
}
