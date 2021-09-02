package com.diplomski.common.simulation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatReport {
	private int min;
	private float lowerQuantile;
	private float median;
	private float upperQuantile;
	private int max;
}
