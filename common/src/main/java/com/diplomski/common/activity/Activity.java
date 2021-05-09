package com.diplomski.common.activity;

import com.diplomski.common.board.BoardState;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Activity {
	private BoardState initialBoardState;
	private BoardState finalBoardState;
	private int initiatingCharacterIndex;
}