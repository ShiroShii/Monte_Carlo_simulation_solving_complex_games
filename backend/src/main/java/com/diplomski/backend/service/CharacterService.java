package com.diplomski.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.diplomski.backend.contract.CharacterRequest;
import com.diplomski.backend.dal.PlayerCharacterDbModel;
import com.diplomski.backend.repository.RepositoryProvider;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CharacterService {
	@Autowired
	private final RepositoryProvider repositoryProvider;
	
	public Optional<PlayerCharacterDbModel> getCharacterModel(UUID id) {
		return repositoryProvider.getCharacterModelRepository().findById(id);
	}
	
	public UUID createCharacter(CharacterRequest request) {
		PlayerCharacterDbModel entity = PlayerCharacterDbModel.builder().build();
		repositoryProvider.getCharacterModelRepository().save(entity);
		return entity.getId();
	}
	
	public void deleteCharacter(UUID id) {
		repositoryProvider.getCharacterModelRepository().deleteById(id);
	}
	
	
	public List<PlayerCharacterDbModel> getAllBoards(){
		return repositoryProvider.getCharacterModelRepository().findAll();
	}
	
	public void editCharacter() {
		
	}
}
