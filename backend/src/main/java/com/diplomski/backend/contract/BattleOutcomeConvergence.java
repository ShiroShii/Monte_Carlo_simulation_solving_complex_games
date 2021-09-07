package com.diplomski.backend.contract;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BattleOutcomeConvergence {
	private int count;
	private float winRate;
	private float drawRate;
	private float lossRate;
}
