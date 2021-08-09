package com.diplomski.backend.contract;

import java.util.UUID;

import com.diplomski.common.character.CharacterClass;
import com.diplomski.common.character.CharacterLevel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCharacterResponse {
	private UUID id;
	private String name;
	private int dexterity;
	private int strength;
	private int walkingSpeed;
	private CharacterClass characterClass;
	private CharacterLevel characterLevel;
}
