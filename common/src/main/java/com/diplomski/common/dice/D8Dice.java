package com.diplomski.common.dice;

public class D8Dice extends AbstractDice {
	@Override
	public int getRoll() {
		return getRandomInteger(1, 8);
	}
}
