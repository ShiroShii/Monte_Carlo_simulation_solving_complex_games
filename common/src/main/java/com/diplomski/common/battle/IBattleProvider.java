package com.diplomski.common.battle;

import java.util.List;

import com.diplomski.common.character.CharacterState;

public interface IBattleProvider {
	public Battle getBattle(List<CharacterState> initialCharacterStates);
}
