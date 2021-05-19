package com.diplomski.common.simulation;

import java.util.HashMap;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartySimulationReport {
	private HashMap<Integer, Float> winRateConvergence;
	
	private float minimumRemainingHpAfterWin;
	private float medianRemainingHpAfterWin;
	private float maximumRemainingHpAfterWin;

	private int minimumMembersWith0HpAfterWin;
	private int medianMembersWith0HpAfterWin;
	private int maximumMembersWith0HpAfterWin;

	// Excluding fumble?
	private int minimumMissCount;
	private int medianMissCount;
	private int maximumMissCount;

	// Excluding critical hit?
	private int minimumHitCount;
	private int medianHitCount;
	private int maximumHitCount;

	private int minimumInflictedDamage;
	private int medianInflictedDamage;
	private int maximumInflictedDamage;

	private int minimumTakenDamage;
	private int medianTakenDamage;
	private int maximumTakenDamage;
}
