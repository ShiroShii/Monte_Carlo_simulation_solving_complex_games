package com.diplomski.common.activity.action.attack;

import com.diplomski.common.character.CharacterType;
import com.diplomski.common.dice.IDiceFactory;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AttackRollOutcomeProviderFactory implements IAttackRollOutcomeProviderFactory {
	private final @NonNull IDiceFactory diceFactory;

	@Override
	public IAttackRollOutcomeProvider getAttackRollOutcomeProvider(CharacterType characterType) {
		return switch (characterType) {
			case PLAYER -> new AttackRollOutcomeProvider(new PlayerAttackRollModifierProvider(), diceFactory);
			case MONSTER -> new AttackRollOutcomeProvider(new MonsterAttackRollModifierProvider(), diceFactory);
		};
	}
}
