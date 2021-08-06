package com.diplomski.common.character;

import java.util.Collection;

import com.diplomski.common.resource.Weapon;
import com.diplomski.common.targeting.TargetingStyle;
import com.diplomski.common.turn.ITurnProvider;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
public class BattleMonsterCharacterState implements IBattleCharacterState{
	private int usedWalkingSpeed;

	private ITurnProvider turnProvider;
	
	private String id;
	
	private Party party;
	
	private int currentHp;
	
	private int armorClass;
	
	private CharacterLevel level;
	
	private PlayStyle playStyle;
	
	private TargetingStyle targetingStyle;
	
	private Collection<Weapon> weapons;
	
	private int dexterity;
	
	private int strength;

	@Override
	public void takeDamage(int damage) {
		currentHp -= damage;
		currentHp = currentHp < 0 ? 0 : currentHp;
	}

	@Override
	public CharacterClass getCharacterClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
