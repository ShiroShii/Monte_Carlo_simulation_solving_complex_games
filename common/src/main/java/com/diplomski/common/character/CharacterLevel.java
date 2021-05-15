package com.diplomski.common.character;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CharacterLevel {
	L1(1, 2), L2(2, 2), L3(3, 2), L4(4, 2), L5(5, 3), L6(6, 3), L7(7, 3), L8(8, 3), L9(9, 4), L10(10, 4), L11(11, 4),
	L12(12, 4), L13(13, 5), L14(14, 5), L15(15, 5), L16(16, 5), L17(17, 6), L18(18, 6), L19(19, 6), L20(20, 6);

	@Getter
	private final int value;

	@Getter
	private final int proficiencyBonus;
}