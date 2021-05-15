package com.diplomski.common.dice;

public class D12Dice extends AbstractDice {
	@Override
	public int getRoll() {
		return getRandomInteger(1, 12);
	}
}
