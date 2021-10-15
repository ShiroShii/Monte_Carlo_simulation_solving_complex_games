package com.diplomski.common.simulation;

import java.util.List;

import com.diplomski.common.board.IBoard;
import com.diplomski.common.character.ICharacterState;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimulationService implements ISimulationService {
	private final ISimulationProvider simulationProvider;
	private final ISimulationReportProvider simulationReportProvider;

	@Override
	public SimulationReport getSimulation(
			List<ICharacterState> initialCharacterState,
			IBoard board,
			int simulationCount,
			int roundCountLimit) {
		Simulation simulation = simulationProvider
				.getSimulation(initialCharacterState, board, simulationCount, roundCountLimit);
		SimulationReport simulationReport = simulationReportProvider.getSimulationReport(simulation);

		return simulationReport;
	}
}
