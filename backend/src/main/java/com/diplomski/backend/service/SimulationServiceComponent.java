package com.diplomski.backend.service;

import static com.diplomski.common.character.Party.ENEMY;
import static com.diplomski.common.character.Party.PLAYER;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.diplomski.common.board.NodeTile;
import com.diplomski.common.character.CharacterClass;
import com.diplomski.common.character.CharacterLevel;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.resource.Weapon;
import com.diplomski.common.simulation.ISimulationService;
import com.diplomski.common.simulation.SimulationReport;
import com.diplomski.common.simulation.SimulationServiceFactory;
import com.diplomski.common.targeting.TargetingStyle;

@Component
public class SimulationServiceComponent {
	private final UUID PLAYER_TILE_ID = UUID.fromString("42d48df1-ccc6-4133-9197-2da414e8a26f");
	private final UUID ENEMY_TILE_ID = UUID.fromString("498c3248-818a-47d8-a692-c7c9069342ab");
	private final String PLAYER_ID = "Player Id";
	private final String ENEMY_ID = "Enemy Id";
	private final int PLAYER_INITIAL_HP = 30;
	private final int ENEMY_INITIAL_HP = 30;
	private final int PLAYER_DEXTERITY = 3;
	private final int ENEMY_DEXTERITY = 3;
	private final int PLAYER_STRENGTH = 20;
	private final int ENEMY_STRENGTH = 20;
	private final PlayStyle PLAYER_PLAY_STYLE = PlayStyle.MELEE_WEAPON_DAMAGE;
	private final PlayStyle ENEMY_PLAY_STYLE = PlayStyle.MELEE_WEAPON_DAMAGE;
	private final TargetingStyle PLAYER_TARGETING_STYLE = TargetingStyle.CLOSEST;
	private final TargetingStyle ENEMY_TARGETING_STYLE = TargetingStyle.CLOSEST;
	private final CharacterLevel PLAYER_LEVEL = CharacterLevel.L3;
	private final CharacterLevel ENEMY_LEVEL = CharacterLevel.L3;
	private final CharacterClass PLAYER_CLASS = CharacterClass.FIGHTER;
	private final CharacterClass ENEMY_CLASS = CharacterClass.FIGHTER;

	private ISimulationService simulationService;

	public SimulationServiceComponent() {
		simulationService = SimulationServiceFactory.getSimulationService();
	}

	public SimulationReport getSimulation(UUID boardStateId, int simulationCount, int roundCountLimit) {

		NodeTile playerTile = NodeTile.builder().id(PLAYER_TILE_ID).build();
		NodeTile enemyTile = NodeTile.builder().id(ENEMY_TILE_ID).build();
		playerTile.setReachableTiles(new HashSet<>(Arrays.asList(enemyTile)));
		enemyTile.setReachableTiles(new HashSet<>(Arrays.asList(playerTile)));

		CharacterState playerCharacterState = CharacterState.builder().id(PLAYER_ID).tile(playerTile)
				.dexterity(PLAYER_DEXTERITY).currentHp(PLAYER_INITIAL_HP).party(PLAYER).strength(PLAYER_STRENGTH)
				.playStyle(PLAYER_PLAY_STYLE).targetingStyle(PLAYER_TARGETING_STYLE).weapons(Arrays.asList(Weapon.CLUB))
				.level(PLAYER_LEVEL).characterClass(PLAYER_CLASS).build();

		CharacterState enemyCharacterState = CharacterState.builder().id(ENEMY_ID).tile(enemyTile)
				.dexterity(ENEMY_DEXTERITY).currentHp(ENEMY_INITIAL_HP).party(ENEMY).strength(ENEMY_STRENGTH)
				.playStyle(ENEMY_PLAY_STYLE).targetingStyle(ENEMY_TARGETING_STYLE).weapons(Arrays.asList(Weapon.CLUB))
				.level(ENEMY_LEVEL).characterClass(ENEMY_CLASS).build();

		List<CharacterState> initialCharacterStates = Arrays.asList(playerCharacterState, enemyCharacterState);

		return simulationService.getSimulation(initialCharacterStates, simulationCount, roundCountLimit);
	}
}
