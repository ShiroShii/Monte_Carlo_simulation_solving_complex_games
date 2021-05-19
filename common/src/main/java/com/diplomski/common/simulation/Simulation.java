package com.diplomski.common.simulation;

import java.util.List;

import com.diplomski.common.battle.Battle;
import com.diplomski.common.character.CharacterState;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Simulation {
	private List<Battle> battles;
	private List<CharacterState> initialCharacterStates;
	private int simulationCount;
	private int roundCountLimit;
}
