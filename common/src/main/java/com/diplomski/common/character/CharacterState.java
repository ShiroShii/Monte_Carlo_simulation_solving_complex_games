package com.diplomski.common.character;

import java.util.List;

import com.diplomski.common.board.ITile;
import com.diplomski.common.resource.Weapon;
import com.diplomski.common.targeting.TargetingStyle;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CharacterState {
	private String id;
	private Party party;
	private int maxHp;
	private int currentHp;
	private int dexterity;
	private int strength;
	private int constitution;
	private int intellect;
	private int wisdom;
	private int armorClass;
	private int exhaustionLevel;
	private CharacterLevel level;
	private CharacterClass characterClass;
	private List<Weapon> weapons;
	private ITile tile;
	private PlayStyle playStyle;
	private TargetingStyle targetingStyle;

	private int walkingSpeed;

	public void takeDamage(int damage) {
		currentHp -= damage;
		currentHp = currentHp < 0 ? 0 : currentHp;
	}

	public abstract static class CharacterStateBuilder<C extends CharacterState, B extends CharacterState.CharacterStateBuilder<C, B>> {
		protected B $fillValuesFromParent(CharacterState instance) {
			$fillValuesFromInstanceIntoBuilder(instance, this);
			return self();
		}
	}
}
