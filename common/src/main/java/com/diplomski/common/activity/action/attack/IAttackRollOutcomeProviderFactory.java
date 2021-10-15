package com.diplomski.common.activity.action.attack;

import com.diplomski.common.character.CharacterType;

public interface IAttackRollOutcomeProviderFactory {
	public IAttackRollOutcomeProvider getAttackRollOutcomeProvider(CharacterType characterType);
}
