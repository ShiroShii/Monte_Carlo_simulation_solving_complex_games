package com.diplomski.common.dice;

public class D6Dice extends AbstractDice {
	@Override
	public int getRoll() {
		return getRandomInteger(1, 6);
	}
}
