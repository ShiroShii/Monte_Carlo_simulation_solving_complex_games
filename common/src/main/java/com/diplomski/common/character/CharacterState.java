package com.diplomski.common.character;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterState{
	private String id;
	private Party party;
	private int maxHp;
	private int currentHp;
	private int dex;
	private int exhaustionLevel;
	private int Tile;
	
	private int walkingSpeed;
	private int usedSpeed;
	
	public void takeDamage(int damage) {
		currentHp -= damage;	
		currentHp = currentHp < 0 ? 0 : currentHp;
	}
}