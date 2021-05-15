package com.diplomski.common.dice;

public class D20Dice extends AbstractDice {
	@Override
	public int getRoll() {
		return getRandomInteger(1, 20);
	}
}
