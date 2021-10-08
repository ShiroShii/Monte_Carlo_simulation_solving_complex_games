package com.diplomski.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.diplomski.backend.contract.PlayerCharacterCreateRequest;
import com.diplomski.backend.dal.PlayerCharacterDbModel;
import com.diplomski.backend.repository.PlayerCharacterRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Transactional
public class PlayerCharacterService {
	private PlayerCharacterRepository playerCharacterRepository;
	
	public PlayerCharacterDbModel save(UUID id, PlayerCharacterCreateRequest request) {
		Optional<PlayerCharacterDbModel> playerCharacter = playerCharacterRepository.findById(id);
		playerCharacter.get().setArmorClass(request.getArmorClass());
		playerCharacter.get().setCharacterClass(request.getCharacterClass());
		playerCharacter.get().setCharacterLevel(request.getCharacterLevel());
		playerCharacter.get().setDexterity(request.getDexterity());
		playerCharacter.get().setName(request.getName());
		playerCharacter.get().setStrength(request.getStrength());
		playerCharacter.get().setWalkingSpeed(request.getWalkingSpeed());
		playerCharacter.get().setWeapons(request.getWeapons());
		
		return playerCharacterRepository.save(playerCharacter.get());
	}

	public PlayerCharacterDbModel save(PlayerCharacterCreateRequest request) {
		PlayerCharacterDbModel playerCharacter = PlayerCharacterDbModel.builder().name(request.getName())
				.dexterity(request.getDexterity()).strength(request.getStrength())
				.weapons(request.getWeapons())
				.armorClass(request.getArmorClass()).walkingSpeed(request.getWalkingSpeed()).characterClass(request.getCharacterClass())
				.characterLevel(request.getCharacterLevel()).build();
		return playerCharacterRepository.save(playerCharacter);
	}

	public Optional<PlayerCharacterDbModel> get(UUID id) {
		return playerCharacterRepository.findById(id);
	}

	public List<PlayerCharacterDbModel> getAll() {
		return playerCharacterRepository.findAll();
	}

	public void delete(UUID id) {
		playerCharacterRepository.deleteById(id);
	}
}
