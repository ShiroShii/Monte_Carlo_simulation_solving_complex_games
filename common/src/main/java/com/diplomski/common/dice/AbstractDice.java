package com.diplomski.common.dice;

public abstract class AbstractDice implements IDice {
	protected int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}
}
