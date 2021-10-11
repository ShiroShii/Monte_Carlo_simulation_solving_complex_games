package com.diplomski.backend.contract;

import java.util.List;
import java.util.UUID;

import com.diplomski.common.character.CharacterClass;
import com.diplomski.common.character.CharacterLevel;
import com.diplomski.common.resource.Weapon;

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
	private int speed;
	private int armorClass;
	private List<Weapon> weapons;
	private CharacterClass characterClass;
	private CharacterLevel characterLevel;
}
