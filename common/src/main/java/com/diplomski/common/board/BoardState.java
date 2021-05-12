package com.diplomski.common.board;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import com.diplomski.common.character.CharacterState;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class BoardState {
	private LinkedHashMap<String, CharacterState> characterStates;
	private HashMap<Tile, TerrainFeature> terainFeatures;
	private HashSet<Tile> obsticles;
}
