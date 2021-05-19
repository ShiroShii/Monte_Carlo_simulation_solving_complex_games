package com.diplomski.common.test.functional;

import static com.diplomski.common.character.Party.ENEMY;
import static com.diplomski.common.character.Party.PLAYER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.activity.AttackRollModifierProvider;
import com.diplomski.common.activity.AttackRollOutcomeProvider;
import com.diplomski.common.activity.IAttackRollModifierProvider;
import com.diplomski.common.activity.IAttackRollOutcomeProvider;
import com.diplomski.common.battle.BattleProvider;
import com.diplomski.common.battle.IBattleProvider;
import com.diplomski.common.board.BoardStateProvider;
import com.diplomski.common.board.IBoardStateProvider;
import com.diplomski.common.board.NodeTile;
import com.diplomski.common.character.CharacterClass;
import com.diplomski.common.character.CharacterLevel;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.damage.ArmorClassProvider;
import com.diplomski.common.damage.DamageProvider;
import com.diplomski.common.damage.IArmorClassProvider;
import com.diplomski.common.damage.IDamageProvider;
import com.diplomski.common.dice.DiceFactory;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.resource.Weapon;
import com.diplomski.common.round.IRoundProvider;
import com.diplomski.common.round.RoundProvider;
import com.diplomski.common.simulation.ISimulationProvider;
import com.diplomski.common.simulation.ISimulationReportProvider;
import com.diplomski.common.simulation.Simulation;
import com.diplomski.common.simulation.SimulationProvider;
import com.diplomski.common.simulation.SimulationReportProvider;
import com.diplomski.common.targeting.TargetingStyle;
import com.diplomski.common.turn.ITurnProviderFactory;
import com.diplomski.common.turn.TurnProviderFactory;

import lombok.NonNull;

public class SimulationReportFunctionalTest {
	private final int SIMULATION_COUNT = 100;
	private final int ROUND_COUNT_LIMIT = 5;
	private final int PLAYER_TILE_ID = 1;
	private final int ENEMY_TILE_ID = 2;
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

	private NodeTile playerTile;
	private NodeTile enemyTile;

	private CharacterState playerCharacterState;
	private CharacterState enemyCharacterState;
	private List<CharacterState> initialCharacterStates;

	private @NonNull ISimulationReportProvider simulationReportProvider;
	private @NonNull ISimulationProvider simulationProvider;
	private @NonNull IBattleProvider battleProvider;
	private @NonNull IBoardStateProvider boardStateProvider;
	private @NonNull IDiceFactory diceFactory;
	private @NonNull ITurnProviderFactory turnProviderFactory;
	private @NonNull IRoundProvider roundProvider;
	private @NonNull IAttackRollOutcomeProvider attackRollOutcomeProvider;
	private @NonNull IDamageProvider damageProvider;
	private @NonNull IAttackRollModifierProvider attackRoleModifierProvider;
	private @NonNull IArmorClassProvider armorClassProvider;

	public void objectSetup() {
		playerTile = NodeTile.builder().id(PLAYER_TILE_ID).build();
		enemyTile = NodeTile.builder().id(ENEMY_TILE_ID).build();
		playerTile.setReachableTiles(new HashSet<>(Arrays.asList(enemyTile)));
		enemyTile.setReachableTiles(new HashSet<>(Arrays.asList(playerTile)));

		playerCharacterState = CharacterState.builder().id(PLAYER_ID).tile(playerTile).dexterity(PLAYER_DEXTERITY)
				.currentHp(PLAYER_INITIAL_HP).party(PLAYER).strengh(PLAYER_STRENGTH).playStyle(PLAYER_PLAY_STYLE)
				.targetingStyle(PLAYER_TARGETING_STYLE).weapons(Arrays.asList(Weapon.CLUB)).level(PLAYER_LEVEL)
				.characterClass(PLAYER_CLASS).build();

		enemyCharacterState = CharacterState.builder().id(ENEMY_ID).tile(enemyTile).dexterity(ENEMY_DEXTERITY)
				.currentHp(ENEMY_INITIAL_HP).party(ENEMY).strengh(ENEMY_STRENGTH).playStyle(ENEMY_PLAY_STYLE)
				.targetingStyle(ENEMY_TARGETING_STYLE).weapons(Arrays.asList(Weapon.CLUB)).level(ENEMY_LEVEL)
				.characterClass(ENEMY_CLASS).build();

		initialCharacterStates = Arrays.asList(playerCharacterState, enemyCharacterState);

	}

	public void serviceSetup() {
		diceFactory = new DiceFactory();
		damageProvider = new DamageProvider();
		armorClassProvider = new ArmorClassProvider();
		attackRoleModifierProvider = new AttackRollModifierProvider();
		attackRollOutcomeProvider = new AttackRollOutcomeProvider(attackRoleModifierProvider, armorClassProvider, diceFactory);
		turnProviderFactory = new TurnProviderFactory(attackRollOutcomeProvider, damageProvider);
		boardStateProvider = new BoardStateProvider(turnProviderFactory, diceFactory);
		roundProvider = new RoundProvider();
		battleProvider = new BattleProvider(boardStateProvider, roundProvider);
		simulationProvider = new SimulationProvider(battleProvider);
		simulationReportProvider = new SimulationReportProvider();
	}

	@Before
	public void setup() {
		objectSetup();
		serviceSetup();
	}

	@Test
	public void generateSimulationReport() {
		Simulation simulation = simulationProvider
				.getSimulation(initialCharacterStates, SIMULATION_COUNT, ROUND_COUNT_LIMIT);

		assertNotNull(simulation);
		assertEquals(SIMULATION_COUNT, simulation.getBattles().size());

		int playerWins = simulation.getBattles().stream()
				.filter(x -> x.isBattleComplete() && x.getWinningParty().get().equals(Party.PLAYER)).toArray().length;

		assertNotEquals("Player always wins.", SIMULATION_COUNT, playerWins);
		assertNotEquals("Enemy always wins.", 0, playerWins);
		// simulationReportProvider.getReport(simulation);
	}
}
