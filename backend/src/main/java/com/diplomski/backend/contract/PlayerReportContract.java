package com.diplomski.backend.contract;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerReportContract {
	private UUID id;
	private String name;
	private PlayerBoxPlot playerBoxPlot;
	private int downCount;
}
