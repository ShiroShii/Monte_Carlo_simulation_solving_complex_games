package com.diplomski.common.simulation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerWinStateReport {
	private StatReport health;
	private StatReport damageDealt;
	private StatReport damageTaken;
}
