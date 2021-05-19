package com.diplomski.common.simulation;

import java.util.List;

import com.diplomski.common.character.CharacterState;

public interface ISimulationService {
	public SimulationReport getSimulation(
			List<CharacterState> initialCharacterState,
			int simulationCount,
			int roundCountLimit);
}
