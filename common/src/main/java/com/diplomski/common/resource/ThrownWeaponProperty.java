package com.diplomski.common.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThrownWeaponProperty {
	private final int normalRange;
	private final int longRange;
}
