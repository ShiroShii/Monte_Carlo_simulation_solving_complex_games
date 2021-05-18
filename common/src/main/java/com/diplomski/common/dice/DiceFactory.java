package com.diplomski.common.dice;

public class DiceFactory implements IDiceFactory {

	@Override
	public IDice getD4() {
		return new D4Dice();
	}

	@Override
	public IDice getD6() {
		return new D6Dice();
	}

	@Override
	public IDice getD8() {
		return new D8Dice();
	}

	@Override
	public IDice getD10() {
		return new D10Dice();
	}

	@Override
	public IDice getD12() {
		return new D12Dice();
	}

	@Override
	public IDice getD20() {
		return new D20Dice();
	}
}
