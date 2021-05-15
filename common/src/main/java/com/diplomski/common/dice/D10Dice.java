package com.diplomski.common.dice;

public class D10Dice extends AbstractDice {
	@Override
	public int getRoll() {
		return getRandomInteger(1, 10);
	}
}
