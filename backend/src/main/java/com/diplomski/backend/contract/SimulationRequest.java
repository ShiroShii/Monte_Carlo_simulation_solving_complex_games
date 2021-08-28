package com.diplomski.backend.contract;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationRequest {
	private UUID battleId;
	private int simulationCount;
	private int roundCountLimit;
}
