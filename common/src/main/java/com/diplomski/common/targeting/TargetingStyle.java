package com.diplomski.common.targeting;

public enum TargetingStyle {
	/**
	 * Target closest character of specific party.
	 */
	CLOSEST,
	/**
	 * Target character of specific party, which has the least remaining hit points,
	 * in relation to their maximum hit points.
	 */
	LEAST_REMAINING_HP,
	/**
	 * Target character of specific party, which has the most remaining hit points,
	 * in relation to their maximum hit points.
	 */
	MOST_REMAINING_HP,
	/**
	 * Target first character of specific party in the character list.
	 */
	ROUND_ROBIN
}
