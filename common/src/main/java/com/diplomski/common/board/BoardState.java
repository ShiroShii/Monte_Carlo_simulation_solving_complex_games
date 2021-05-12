package com.diplomski.common.board;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import com.diplomski.common.character.BattleCharacterState;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class BoardState {
	private LinkedHashMap<String, BattleCharacterState> characterStates;
	private HashMap<Tile, TerrainFeature> terainFeatures;
	private HashSet<Tile> obsticles;
}
