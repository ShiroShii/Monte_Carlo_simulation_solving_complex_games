package com.diplomski.backend.contract;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DownedPlayerContract {
	private int downedCount;
	private float downedPercentage;
	private int simulationCount;
}
