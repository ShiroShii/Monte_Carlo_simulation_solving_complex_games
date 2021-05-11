package com.diplomski.common.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public abstract class TerrainFeatureType {
	private MovementDificulty movementDificulty;
	private Concealment concealment;
}
