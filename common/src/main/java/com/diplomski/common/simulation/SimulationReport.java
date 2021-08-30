package com.diplomski.common.simulation;

import java.util.HashMap;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimulationReport {
	private PartySimulationReport playerPartyReport;
	private PartySimulationReport enemyPartyReport;
	private HashMap<Integer, Float> drawRateConvergence;
	private int winCount;
	private int lossCount;
	private int drawCount;
	private int simulationCount;
	private int roundCountLimit;
}
