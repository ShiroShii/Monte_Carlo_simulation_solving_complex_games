package com.diplomski.common.turn;

import java.util.List;
import java.util.UUID;

import com.diplomski.common.activity.Activity;
import com.diplomski.common.board.BoardState;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Turn {
	private List<Activity> activities;
	private UUID initiatorId;
	private BoardState initialBoardState;
	private BoardState finalBoardState;
}

//no turn delays
//no prepared actions
