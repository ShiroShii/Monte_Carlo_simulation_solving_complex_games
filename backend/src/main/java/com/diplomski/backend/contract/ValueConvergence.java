package com.diplomski.backend.contract;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValueConvergence {
	private int count;
	private float value;
}
