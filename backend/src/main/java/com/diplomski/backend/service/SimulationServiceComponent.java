package com.diplomski.backend.service;

import static com.diplomski.common.character.Party.ENEMY;
import static com.diplomski.common.character.Party.PLAYER;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.diplomski.common.board.NodeBoard;
import com.diplomski.common.board.NodeTile;
import com.diplomski.common.board.TerrainFeature;
import com.diplomski.common.character.CharacterClass;
import com.diplomski.common.character.CharacterLevel;
import com.diplomski.common.character.ICharacterState;
import com.diplomski.common.character.Monster;
import com.diplomski.common.character.MonsterCharacterState;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.character.PlayerCharacterState;
import com.diplomski.common.resource.Weapon;
import com.diplomski.common.simulation.ISimulationService;
import com.diplomski.common.simulation.SimulationReport;
import com.diplomski.common.simulation.SimulationServiceFactory;
import com.diplomski.common.targeting.TargetingStyle;

@Component
public class SimulationServiceComponent {
	private final UUID PLAYER_TILE_ID = UUID.fromString("42d48df1-ccc6-4133-9197-2da414e8a26f");
	private final UUID DISTANCE_TILE_1_ID = UUID.fromString("286cc538-9b1c-47f7-a2c2-0c8fca96e198");
	private final UUID DISTANCE_TILE_2_ID = UUID.fromString("b49a44bf-088a-4ef1-aa11-5a10edadf3a2");
	private final UUID DISTANCE_TILE_3_ID = UUID.fromString("bcc47118-5c85-4f14-a07d-6d87f1527ced");
	private final UUID DISTANCE_TILE_4_ID = UUID.fromString("eaea20ba-a045-4bb0-bfac-28458361058e");
	private final UUID DISTANCE_TILE_5_ID = UUID.fromString("063a7d57-2f01-496d-bcbe-d9fb4886a933");
	private final UUID DISTANCE_TILE_51_ID = UUID.fromString("b55665f6-004b-459c-b69e-e6b89a39ab32");
	private final UUID DISTANCE_TILE_52_ID = UUID.fromString("993379e2-84ce-43c5-b56d-3b28162ce6cb");
	private final UUID DISTANCE_TILE_6_ID = UUID.fromString("4d7dbb8d-8168-46e6-a33d-e71a456d6f9a");
	private final UUID DISTANCE_TILE_7_ID = UUID.fromString("058b1670-2812-4bcb-ab35-157be8df41e7");
	private final UUID DISTANCE_TILE_8_ID = UUID.fromString("7ea566e5-aa72-4628-9efa-f0371b235eec");
	private final UUID ENEMY_TILE_ID = UUID.fromString("498c3248-818a-47d8-a692-c7c9069342ab");
	private final String PLAYER_ID = "Player Id";
	private final String ENEMY_ID = "Enemy Id";
	private final int PLAYER_INITIAL_HP = 20;
	private final int PLAYER_DEXTERITY = 10;
	private final int PLAYER_STRENGTH = 10;
	private final int PLAYER_SPEED = 6;
	private final PlayStyle PLAYER_PLAY_STYLE = PlayStyle.MELEE_WEAPON_DAMAGE;
	private final PlayStyle ENEMY_PLAY_STYLE = PlayStyle.MELEE_WEAPON_DAMAGE;
	private final TargetingStyle PLAYER_TARGETING_STYLE = TargetingStyle.CLOSEST;
	private final TargetingStyle ENEMY_TARGETING_STYLE = TargetingStyle.CLOSEST;
	private final CharacterLevel PLAYER_LEVEL = CharacterLevel.L3;
	private final CharacterClass PLAYER_CLASS = CharacterClass.FIGHTER;

	private NodeTile playerTile;
	private NodeTile distanceTile1;
	private NodeTile distanceTile2;
	private NodeTile distanceTile3;
	private NodeTile distanceTile4;
	private NodeTile distanceTile5;
	private NodeTile distanceTile51;
	private NodeTile distanceTile52;
	private NodeTile distanceTile6;
	private NodeTile distanceTile7;
	private NodeTile distanceTile8;
	private NodeTile enemyTile;
	private NodeBoard board;

	private PlayerCharacterState playerCharacterState;
	private MonsterCharacterState enemyCharacterState;
	private List<ICharacterState> initialCharacterStates;

	private ISimulationService simulationService;

	public SimulationServiceComponent() {
		simulationService = SimulationServiceFactory.getSimulationService();
	}

	public SimulationReport getSimulation(UUID boardStateId, int simulationCount, int roundCountLimit) {

		playerTile = NodeTile.builder().id(PLAYER_TILE_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		distanceTile1 = NodeTile.builder().id(DISTANCE_TILE_1_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		distanceTile2 = NodeTile.builder().id(DISTANCE_TILE_2_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		distanceTile3 = NodeTile.builder().id(DISTANCE_TILE_3_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		distanceTile4 = NodeTile.builder().id(DISTANCE_TILE_4_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		distanceTile5 = NodeTile.builder().id(DISTANCE_TILE_5_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		distanceTile51 = NodeTile.builder().id(DISTANCE_TILE_51_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		distanceTile52 = NodeTile.builder().id(DISTANCE_TILE_52_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		distanceTile6 = NodeTile.builder().id(DISTANCE_TILE_6_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		distanceTile7 = NodeTile.builder().id(DISTANCE_TILE_7_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		distanceTile8 = NodeTile.builder().id(DISTANCE_TILE_8_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		enemyTile = NodeTile.builder().id(ENEMY_TILE_ID).terrainType(TerrainFeature.LOW_GRASS).build();
		playerTile.setReachableTiles(new HashSet<>(Arrays.asList(DISTANCE_TILE_1_ID)));
		distanceTile1.setReachableTiles(new HashSet<>(Arrays.asList(PLAYER_TILE_ID, DISTANCE_TILE_2_ID)));
		distanceTile2.setReachableTiles(new HashSet<>(Arrays.asList(DISTANCE_TILE_1_ID, DISTANCE_TILE_3_ID)));
		distanceTile3.setReachableTiles(new HashSet<>(Arrays.asList(DISTANCE_TILE_2_ID, DISTANCE_TILE_4_ID)));
		distanceTile4.setReachableTiles(new HashSet<>(Arrays.asList(DISTANCE_TILE_3_ID, DISTANCE_TILE_5_ID)));
		distanceTile5.setReachableTiles(new HashSet<>(Arrays
				.asList(DISTANCE_TILE_4_ID, DISTANCE_TILE_51_ID, DISTANCE_TILE_6_ID)));
		distanceTile51.setReachableTiles(new HashSet<>(Arrays.asList(DISTANCE_TILE_5_ID, DISTANCE_TILE_52_ID)));
		distanceTile52.setReachableTiles(new HashSet<>(Arrays.asList(DISTANCE_TILE_51_ID)));
		distanceTile6.setReachableTiles(new HashSet<>(Arrays.asList(DISTANCE_TILE_5_ID, DISTANCE_TILE_7_ID)));
		distanceTile7.setReachableTiles(new HashSet<>(Arrays.asList(DISTANCE_TILE_6_ID, DISTANCE_TILE_8_ID)));
		distanceTile8.setReachableTiles(new HashSet<>(Arrays.asList(DISTANCE_TILE_7_ID, ENEMY_TILE_ID)));
		enemyTile.setReachableTiles(new HashSet<>(Arrays.asList(DISTANCE_TILE_8_ID)));
		
		HashMap<UUID, NodeTile> tiles = new HashMap<>();
		tiles.put(DISTANCE_TILE_1_ID, distanceTile1);
		tiles.put(DISTANCE_TILE_2_ID, distanceTile2);
		tiles.put(DISTANCE_TILE_3_ID, distanceTile3);
		tiles.put(DISTANCE_TILE_4_ID, distanceTile4);
		tiles.put(DISTANCE_TILE_5_ID, distanceTile5);
		tiles.put(DISTANCE_TILE_51_ID, distanceTile51);
		tiles.put(DISTANCE_TILE_52_ID, distanceTile52);
		tiles.put(DISTANCE_TILE_6_ID, distanceTile6);
		tiles.put(DISTANCE_TILE_7_ID, distanceTile7);
		tiles.put(DISTANCE_TILE_8_ID, distanceTile8);
		tiles.put(PLAYER_TILE_ID, playerTile);
		tiles.put(ENEMY_TILE_ID, enemyTile);

		board = NodeBoard.builder().tiles(tiles).build();

		playerCharacterState = PlayerCharacterState.builder().id(PLAYER_ID).tileId(PLAYER_TILE_ID)
				.dexterity(PLAYER_DEXTERITY).currentHp(PLAYER_INITIAL_HP).party(PLAYER).strength(PLAYER_STRENGTH)
				.playStyle(PLAYER_PLAY_STYLE).targetingStyle(PLAYER_TARGETING_STYLE).walkingSpeed(PLAYER_SPEED)
				.resources(Arrays.asList(Weapon.CLUB)).level(PLAYER_LEVEL).characterClass(PLAYER_CLASS).build();

		enemyCharacterState = MonsterCharacterState.builder().monster(Monster.GIANT_RAT).id(ENEMY_ID)
				.tileId(ENEMY_TILE_ID).party(ENEMY).playStyle(ENEMY_PLAY_STYLE).targetingStyle(ENEMY_TARGETING_STYLE)
				.build();

		initialCharacterStates = Arrays.asList(playerCharacterState, enemyCharacterState);

		return simulationService.getSimulation(initialCharacterStates, board, simulationCount, roundCountLimit);
	}
}
