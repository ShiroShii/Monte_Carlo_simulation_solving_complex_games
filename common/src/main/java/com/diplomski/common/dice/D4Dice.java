package com.diplomski.common.dice;

public class D4Dice extends AbstractDice {
	@Override
	public int getRoll() {
		return getRandomInteger(1, 4);
	}
}
