package com.diplomski.common.test.functional;

import static com.diplomski.common.character.Party.ENEMY;
import static com.diplomski.common.character.Party.PLAYER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.activity.AttackRollOutcomeProviderFactory;
import com.diplomski.common.activity.IAttackRollOutcomeProviderFactory;
import com.diplomski.common.battle.BattleProvider;
import com.diplomski.common.battle.IBattleProvider;
import com.diplomski.common.board.BoardStateProvider;
import com.diplomski.common.board.IBoardStateProvider;
import com.diplomski.common.board.INavigator;
import com.diplomski.common.board.NodeBoard;
import com.diplomski.common.board.NodeNavigator;
import com.diplomski.common.board.NodeTile;
import com.diplomski.common.character.CharacterClass;
import com.diplomski.common.character.CharacterLevel;
import com.diplomski.common.character.ICharacterState;
import com.diplomski.common.character.Monster;
import com.diplomski.common.character.MonsterCharacterState;
import com.diplomski.common.character.Party;
import com.diplomski.common.character.PlayStyle;
import com.diplomski.common.character.PlayerCharacterState;
import com.diplomski.common.damage.DamageProvider;
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

public class PlayerVsMonsterSimulationReportFunctionalTest {
	private final int SIMULATION_COUNT = 10000;
	private final int ROUND_COUNT_LIMIT = 5;
	private final UUID PLAYER_TILE_ID = UUID.fromString("42d48df1-ccc6-4133-9197-2da414e8a26f");
	private final UUID ENEMY_TILE_ID = UUID.fromString("498c3248-818a-47d8-a692-c7c9069342ab");
	private final UUID PLAYER_ID = UUID.fromString("8b521099-18fd-4810-953d-bc4dde0eae14");
	private final UUID ENEMY_ID = UUID.fromString("3e5aee3a-41e6-402c-a42d-6da8adc7cac9");
	private final int PLAYER_INITIAL_HP = 20;
	private final int PLAYER_DEXTERITY = 10;
	private final int PLAYER_STRENGTH = 10;
	private final PlayStyle PLAYER_PLAY_STYLE = PlayStyle.MELEE_WEAPON_DAMAGE;
	private final PlayStyle ENEMY_PLAY_STYLE = PlayStyle.MELEE_WEAPON_DAMAGE;
	private final TargetingStyle PLAYER_TARGETING_STYLE = TargetingStyle.CLOSEST;
	private final TargetingStyle ENEMY_TARGETING_STYLE = TargetingStyle.CLOSEST;
	private final CharacterLevel PLAYER_LEVEL = CharacterLevel.L3;
	private final CharacterClass PLAYER_CLASS = CharacterClass.FIGHTER;

	private NodeTile playerTile;
	private NodeTile enemyTile;
	private NodeBoard board;

	private PlayerCharacterState playerCharacterState;
	private MonsterCharacterState enemyCharacterState;
	private List<ICharacterState> initialCharacterStates;

	private @NonNull ISimulationReportProvider simulationReportProvider;
	private @NonNull ISimulationProvider simulationProvider;
	private @NonNull IBattleProvider battleProvider;
	private @NonNull IBoardStateProvider boardStateProvider;
	private @NonNull IDiceFactory diceFactory;
	private @NonNull INavigator navigator;
	private @NonNull ITurnProviderFactory turnProviderFactory;
	private @NonNull IRoundProvider roundProvider;
	private @NonNull IAttackRollOutcomeProviderFactory attackRollOutcomeProviderFactory;
	private @NonNull IDamageProvider damageProvider;

	public void objectSetup() {
		playerTile = NodeTile.builder().id(PLAYER_TILE_ID).build();
		enemyTile = NodeTile.builder().id(ENEMY_TILE_ID).build();
		playerTile.setReachableTiles(new HashSet<>(Arrays.asList(ENEMY_TILE_ID)));
		enemyTile.setReachableTiles(new HashSet<>(Arrays.asList(PLAYER_TILE_ID)));
		board = NodeBoard.builder().tiles(new HashMap<>(Map.of(PLAYER_TILE_ID, playerTile, ENEMY_TILE_ID, enemyTile)))
				.build();

		playerCharacterState = PlayerCharacterState.builder().id(PLAYER_ID).tileId(PLAYER_TILE_ID)
				.dexterity(PLAYER_DEXTERITY).currentHp(PLAYER_INITIAL_HP).party(PLAYER).strength(PLAYER_STRENGTH)
				.playStyle(PLAYER_PLAY_STYLE).targetingStyle(PLAYER_TARGETING_STYLE)
				.resources(Arrays.asList(Weapon.CLUB)).level(PLAYER_LEVEL).characterClass(PLAYER_CLASS).build();

		enemyCharacterState = MonsterCharacterState.builder().monster(Monster.GIANT_RAT).id(ENEMY_ID)
				.tileId(ENEMY_TILE_ID).party(ENEMY).playStyle(ENEMY_PLAY_STYLE).targetingStyle(ENEMY_TARGETING_STYLE)
				.build();

		initialCharacterStates = Arrays.asList(playerCharacterState, enemyCharacterState);

	}

	public void serviceSetup() {
		diceFactory = new DiceFactory();
		damageProvider = new DamageProvider(diceFactory);
		attackRollOutcomeProviderFactory = new AttackRollOutcomeProviderFactory(diceFactory);
		navigator = new NodeNavigator();
		turnProviderFactory = new TurnProviderFactory(attackRollOutcomeProviderFactory, damageProvider, navigator);
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
				.getSimulation(initialCharacterStates, board, SIMULATION_COUNT, ROUND_COUNT_LIMIT);

		assertNotNull(simulation);
		assertEquals(SIMULATION_COUNT, simulation.getBattles().size());

		int playerWins = simulation.getBattles().stream()
				.filter(x -> x.isBattleComplete() && x.getWinningParty().get().equals(Party.PLAYER)).toArray().length;

		int enemyWins = simulation.getBattles().stream()
				.filter(x -> x.isBattleComplete() && x.getWinningParty().get().equals(Party.ENEMY)).toArray().length;

		int draws = simulation.getBattles().stream().filter(x -> !x.isBattleComplete()).toArray().length;

		assertNotEquals("Player always wins", playerWins, SIMULATION_COUNT);
		assertNotEquals("Enemy always wins", enemyWins, SIMULATION_COUNT);
		assertNotEquals("All battles are a draw", draws, SIMULATION_COUNT);
		// System.out.println((double) playerWins / SIMULATION_COUNT);
	}
}
