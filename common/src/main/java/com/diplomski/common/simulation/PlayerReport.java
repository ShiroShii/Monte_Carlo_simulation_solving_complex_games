package com.diplomski.common.simulation;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerReport {
	private UUID id;
	private String name;
	private PlayerWinStateReport playerBoxPlot;
	private int downCount;
}
