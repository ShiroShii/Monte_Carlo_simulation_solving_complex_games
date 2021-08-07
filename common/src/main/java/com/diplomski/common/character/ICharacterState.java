package com.diplomski.common.character;

import com.diplomski.common.targeting.TargetingStyle;

public interface ICharacterState {
	int getDexterity();

	Party getParty();

	String getId();

	TargetingStyle getTargetingStyle();

	PlayStyle getPlayStyle();
}
