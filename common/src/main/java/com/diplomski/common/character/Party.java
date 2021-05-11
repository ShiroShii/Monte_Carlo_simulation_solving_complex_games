package com.diplomski.common.character;

public enum Party {
	PLAYER, ENEMY;

	public Party getOpponentParty() {
		switch (this) {
		case ENEMY: {
			return PLAYER;
		}
		case PLAYER:
		default: {
			return ENEMY;
		}
		}
	}
}
