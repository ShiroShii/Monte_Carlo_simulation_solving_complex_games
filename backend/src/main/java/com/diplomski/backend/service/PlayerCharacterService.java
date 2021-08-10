package com.diplomski.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.diplomski.backend.contract.PlayerCharacterCreateRequest;
import com.diplomski.backend.dal.PlayerCharacterDbModel;
import com.diplomski.backend.repository.PlayerCharacterRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PlayerCharacterService {
	private PlayerCharacterRepository playerCharacterRepository;

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
