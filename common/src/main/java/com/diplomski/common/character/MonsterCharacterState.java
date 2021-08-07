package com.diplomski.common.character;

import com.diplomski.common.board.ITile;
import com.diplomski.common.targeting.TargetingStyle;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MonsterCharacterState implements ICharacterState{
	private String id;
	private Party party;
	protected Monster monster;
	private ITile tile;
	private PlayStyle playStyle;
	private TargetingStyle targetingStyle;

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
}
