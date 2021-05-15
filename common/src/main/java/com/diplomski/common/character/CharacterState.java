package com.diplomski.common.character;

import java.util.List;

import com.diplomski.common.activity.Weapon;
import com.diplomski.common.board.Tile;
import com.diplomski.common.targeting.TargetingStyle;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CharacterState {
	private String id;
	private Party party;
	private int maxHp;
	private int currentHp;
	private int dexterity;
	private int exhaustionLevel;
	private CharacterLevel level;
	private CharacterClass characterClass;
	private List<Weapon> weapons;
	private Tile tile;
	private PlayStyle playStyle;
	private TargetingStyle targetingStyle;

	private int walkingSpeed;

	public void takeDamage(int damage) {
		currentHp -= damage;
		currentHp = currentHp < 0 ? 0 : currentHp;
	}
}
