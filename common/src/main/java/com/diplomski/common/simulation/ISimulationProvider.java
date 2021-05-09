package com.diplomski.common.simulation;

import java.util.List;

import com.diplomski.common.character.CharacterState;

public interface ISimulationProvider {
	public Simulation getSimulation(List<CharacterState> initialCharacterState, int simulationCount);
}
