package com.diplomski.common.board;

import java.util.List;

import com.diplomski.common.character.CharacterState;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class BoardState {
	private List<CharacterState> characterStates; // TODO: switch to LinkedHashMap
	private List<TerrainFeature> terainFeatures; // TODO: switch to HashMap
	private List<Tile> obsticles;// TODO: switch to HashMap
}
