package com.diplomski.common.simulation;

import lombok.Value;

@Value
public class Pair<K, V> {
	K _1;
	V _2;

	public String toString() {
		return _1 + ":" + _2;
	}
}
