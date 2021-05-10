package com.diplomski.common.board;

import java.util.List;

import com.diplomski.common.character.CharacterState;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder=true)
public class BoardState {
	private List<CharacterState> characterStates;
	// private List<TerrainModifier> terrainModifiers;
	// Regular terrain, Difficult terrain, Impassible Terrain
}
