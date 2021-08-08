package com.diplomski.common.damage;

import java.util.List;

import com.diplomski.common.dice.DiceType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DamageRoll {
	private List<DiceType> dice;
	private int rollAddend;
}
