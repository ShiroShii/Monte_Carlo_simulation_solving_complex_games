package com.diplomski.common.dice;

public class DiceFactory implements IDiceFactory {
	@Override
	public IDice getDice(DiceType diceType) {
		return switch (diceType) {
			case D4 -> new D4Dice();
			case D6 -> new D6Dice();
			case D8 -> new D8Dice();
			case D10 -> new D10Dice();
			case D12 -> new D12Dice();
			case D20 -> new D20Dice();
		};
	}
}
