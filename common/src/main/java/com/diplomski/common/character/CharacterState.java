package com.diplomski.common.character;

import com.diplomski.common.board.Tile;

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
	private int dex;
	private int exhaustionLevel;
	private Tile tile;

	private int walkingSpeed;

	public void takeDamage(int damage) {
		currentHp -= damage;
		currentHp = currentHp < 0 ? 0 : currentHp;
	}
}
