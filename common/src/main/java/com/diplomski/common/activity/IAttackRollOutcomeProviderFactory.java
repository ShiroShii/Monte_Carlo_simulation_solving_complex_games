package com.diplomski.common.activity;

import com.diplomski.common.character.CharacterType;
import com.diplomski.common.character.PlayStyle;

public interface IAttackRollOutcomeProviderFactory {
	public IAttackRollOutcomeProvider getAttackRollOutcomeProvider(CharacterType characterType, PlayStyle playStyle);
}
