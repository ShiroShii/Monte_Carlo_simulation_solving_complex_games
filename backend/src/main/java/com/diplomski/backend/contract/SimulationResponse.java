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
	private List<BattleOutcomeConvergence> battleOutcomeConvergence;
	private List<NameValueIntPair> battleOutcomeSlices;
	private int simulationCount;
	private int roundCountLimit;
	private UUID battleId;
}
