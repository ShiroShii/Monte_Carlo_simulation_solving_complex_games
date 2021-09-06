package com.diplomski.backend.contract;

import java.util.List;
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
	private PlayerBoxPlot playerBoxPlot;
	private List<BattleOutcomeConvergence> battleOutcomeConvergence;
	private List<NameValueIntPair> battleOutcomeSlices;
	private List<NameValueIntPair> battleOutcomeBars;
	private List<DownedPlayerContract> downedPlayers;
	private List<PlayerReportContract> playerReports;
	private int initialPlayerCount;
	private int simulationCount;
	private int roundCountLimit;
	private UUID battleId;
}
