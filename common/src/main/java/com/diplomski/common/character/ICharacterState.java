package com.diplomski.common.character;

import java.util.UUID;

import com.diplomski.common.targeting.TargetingStyle;

public interface ICharacterState {
	UUID getTileId();

	int getDexterity();

	int getSpeed();

	int getCurrentHp();

	Party getParty();

	UUID getId();

	TargetingStyle getTargetingStyle();

	PlayStyle getPlayStyle();
}
