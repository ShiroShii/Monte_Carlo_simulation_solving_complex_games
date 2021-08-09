package com.diplomski.common.character;

import java.util.UUID;

import com.diplomski.common.targeting.TargetingStyle;

public interface ICharacterState {
	UUID getTileId();
	int getDexterity();
	int getWalkingSpeed();

	Party getParty();

	String getId();

	TargetingStyle getTargetingStyle();

	PlayStyle getPlayStyle();
}
