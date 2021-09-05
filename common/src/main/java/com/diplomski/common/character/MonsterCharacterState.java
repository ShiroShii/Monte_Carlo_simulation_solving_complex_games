package com.diplomski.common.character;

import java.util.UUID;

import com.diplomski.common.targeting.TargetingStyle;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MonsterCharacterState implements ICharacterState{
	private UUID id;
	private Party party;
	protected Monster monster;
	private UUID tileId;
	private PlayStyle playStyle;
	private TargetingStyle targetingStyle;
	private int currentHp;

	public abstract static class MonsterCharacterStateBuilder<C extends MonsterCharacterState, B extends MonsterCharacterState.MonsterCharacterStateBuilder<C, B>> {
		protected B $fillValuesFromParent(MonsterCharacterState instance) {
			$fillValuesFromInstanceIntoBuilder(instance, this);
			return self();
		}
	}

	@Override
	public int getDexterity() {
		return this.monster.getDexterity();
	}

	@Override
	public int getWalkingSpeed() {
		return this.monster.getWalkingSpeed();
	}
}
