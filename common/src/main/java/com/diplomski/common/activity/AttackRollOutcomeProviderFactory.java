package com.diplomski.common.activity;

import com.diplomski.common.character.CharacterType;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.dice.IDiceFactory;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AttackRollOutcomeProviderFactory implements IAttackRollOutcomeProviderFactory {
	private final @NonNull IDiceFactory diceFactory;

	@Override
	public IAttackRollOutcomeProvider getAttackRollOutcomeProvider(CharacterType characterType, PlayStyle playStyle) {
		return switch (characterType) {
			case PLAYER -> switch (playStyle) {
					case MELEE_WEAPON_DAMAGE -> new AttackRollOutcomeProvider(new PlayerMeleeAttackRollModifierProvider(), diceFactory);
					default -> throw new IllegalArgumentException("PlayStyle not implemented.");
				};
			case MONSTER -> new AttackRollOutcomeProvider(new MonsterAttackRollModifierProvider(), diceFactory);
		};
	}
}
