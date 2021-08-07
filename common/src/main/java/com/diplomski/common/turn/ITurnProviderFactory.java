package com.diplomski.common.turn;

import com.diplomski.common.character.CharacterType;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.targeting.TargetingStyle;

public interface ITurnProviderFactory {
	public <T> ITurnProvider getTurnProvider(
			String id,
			Party party,
			PlayStyle playStyle,
			TargetingStyle targetingStyle,
			CharacterType characterType);
}
