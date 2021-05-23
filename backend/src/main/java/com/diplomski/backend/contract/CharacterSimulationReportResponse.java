package com.diplomski.backend.contract;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSimulationReportResponse {
	private HashMap<Integer, Float> remainingHealthConvergence;
	private HashMap<Integer, Float> deathRateConvergence;
}
