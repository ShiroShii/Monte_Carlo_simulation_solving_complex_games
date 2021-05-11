package com.diplomski.common.simulation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimulationReport {
	private PartySimulationReport playerReport;
	private PartySimulationReport enemyReport;

	private Simulation simulation;
}
