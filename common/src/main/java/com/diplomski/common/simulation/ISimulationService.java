package com.diplomski.common.simulation;

import java.util.List;

import com.diplomski.common.character.ICharacterState;

public interface ISimulationService {
	public SimulationReport getSimulation(
			List<ICharacterState> initialCharacterState,
			int simulationCount,
			int roundCountLimit);
}
