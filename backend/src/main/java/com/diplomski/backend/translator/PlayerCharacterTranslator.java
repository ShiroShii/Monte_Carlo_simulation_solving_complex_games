package com.diplomski.backend.translator;

import java.util.List;

import com.diplomski.backend.contract.PlayerCharacterResponse;
import com.diplomski.backend.dal.PlayerCharacterDbModel;

public class PlayerCharacterTranslator {
	public static PlayerCharacterResponse translate(PlayerCharacterDbModel input) {
		return PlayerCharacterResponse.builder()
				.id(input.getId())
				.name(input.getName())
				.dexterity(input.getDexterity())
				.strength(input.getStrength())
				.speed(input.getSpeed())
				.weapons(input.getWeapons())
				.armorClass(input.getArmorClass())
				.characterClass(input.getCharacterClass())
				.characterLevel(input.getCharacterLevel())
				.build();
	}

	public static List<PlayerCharacterResponse> translate(
			List<PlayerCharacterDbModel> input) {
		return input.stream().map(x -> translate(x)).toList();
	}
}
