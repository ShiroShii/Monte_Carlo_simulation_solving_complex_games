package com.diplomski.common.turn;

import java.util.List;

import com.diplomski.common.activity.Activity;
import com.diplomski.common.board.BoardState;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Turn {
	private List<Activity> activities;
	private String initiatorId;
	private BoardState initialBoardState;
	private BoardState finalBoardState;
}

//no turn delays
//no prepared actions