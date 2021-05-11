package com.diplomski.common.character;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class InitialCharacterState {
	private String id;
	private Party party;
	private int maxHp;
	private int currentHp;
	private int dex;
	private int exhaustionLevel;
	private int Tile;

	private int walkingSpeed;

	public void takeDamage(int damage) {
		currentHp -= damage;
		currentHp = currentHp < 0 ? 0 : currentHp;
	}
}
