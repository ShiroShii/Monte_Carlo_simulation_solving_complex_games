package com.diplomski.common.simulation;

import java.util.List;

import com.diplomski.common.character.ICharacterState;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimulationService implements ISimulationService {
	private final ISimulationProvider simulationProvider;
	private final ISimulationReportProvider simulationReportProvider;

	@Override
	public SimulationReport getSimulation(
			List<ICharacterState> initialCharacterState,
			int simulationCount,
			int roundCountLimit) {
		Simulation simulation = simulationProvider.getSimulation(initialCharacterState, simulationCount, roundCountLimit);
		SimulationReport simulationReport = simulationReportProvider.getSimulationReport(simulation);
		
		return simulationReport;
	}

}
