package com.diplomski.common.simulation;

import java.util.HashMap;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimulationReport {
	private HashMap<Integer, Float> winRateConvergence;
	private HashMap<Integer, Float> drawRateConvergence;
	private List<Pair<Pair<Integer, Integer>, Integer>> outcomes;
	private List<Pair<Integer, Integer>> downedPlayers;
	private int winCount;
	private int lossCount;
	private int drawCount;
	private int simulationCount;
	private int roundCountLimit;
	private int initialPlayerCount;
	private PlayerWinStateReport playerWinStateReport;
}
