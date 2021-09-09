package com.diplomski.common.board;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Cover {
	NO_COVER(Optional.of(0)),
	HALF_COVER(Optional.of(2)),
	THREE_QUARTER_COVER(Optional.of(5)),
	FULL_COVER(Optional.empty());
	
	@Getter
	private Optional<Integer> bonus;
}
