package com.diplomski.backend.translator;

import com.diplomski.backend.contract.PlayerCharacterResponse;
import com.diplomski.backend.dal.PlayerCharacterDbModel;

public class PlayerCharacterTranslator {
	public static PlayerCharacterResponse translate(PlayerCharacterDbModel input) {
		return PlayerCharacterResponse.builder().id(input.getId()).name(input.getName()).dexterity(input.getDexterity())
				.strength(input.getStrength()).walkingSpeed(input.getWalkingSpeed())
				.weapons(input.getWeapons())
				.armorClass(input.getArmorClass()).characterClass(input.getCharacterClass()).characterLevel(input.getCharacterLevel()).build();
	}
}
