package com.diplomski.backend.contract;

import java.util.HashMap;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationResponse {
	private PartySimulationReportResponse playerPartySimulationReport;
	private PartySimulationReportResponse enemyPartySimulationReport;
	private HashMap<Integer, Float> drawRateConvergence;
	private int simulationCount;
	private int roundCountLimit;
	private UUID boardStateId;
}
