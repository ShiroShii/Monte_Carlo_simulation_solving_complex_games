package com.diplomski.common.character;

import java.util.List;

import com.diplomski.common.resource.IResource;
import com.diplomski.common.turn.ITurnProvider;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MonsterBattleCharacterState extends MonsterCharacterState implements IBattleCharacterState {
	private int currentHp;

	private int usedWalkingSpeed;

	private ITurnProvider turnProvider;

	public static MonsterBattleCharacterState getBattleState(
			MonsterCharacterState characterState,
			ITurnProvider turnProvider) {
		return MonsterBattleCharacterState.toBuilder(characterState).currentHp(characterState.getCurrentHp())
				.turnProvider(turnProvider).usedWalkingSpeed(0).build();
	}

	public static MonsterBattleCharacterStateBuilder<?, ?> toBuilder(MonsterCharacterState characterState) {
		return new MonsterBattleCharacterStateBuilderImpl().$fillValuesFromParent(characterState);
	}

	public int getArmorClass() {
		return this.monster.getArmorClass();
	}

	@Override
	public void takeDamage(int damage) {
		this.currentHp -= damage;
		this.currentHp = this.currentHp < 0 ? 0 : this.currentHp;
	}

	@Override
	public List<IResource> getResources() {
		return this.monster.getAttack();
	}
}
