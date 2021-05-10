package com.diplomski.common.activity;

import com.diplomski.common.board.BoardState;

//TODO: implement SavingThrowActivityProvider, ActionActivityProvider, MovementActivityProvider, PassiveEffectActivityProvider
public interface IActivityProvider {
	public Activity getActivity(int initiatorIndex, BoardState initialBoardState);
}
