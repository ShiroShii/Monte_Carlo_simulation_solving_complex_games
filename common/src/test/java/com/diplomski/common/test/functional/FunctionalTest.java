package com.diplomski.common.test.functional;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.diplomski.common.activity.IAttackRollOutcomeProvider;
import com.diplomski.common.activity.IDamageProvider;
import com.diplomski.common.battle.BattleProvider;
import com.diplomski.common.battle.IBattleProvider;
import com.diplomski.common.board.BoardStateProvider;
import com.diplomski.common.board.IBoardStateProvider;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.round.IRoundProvider;
import com.diplomski.common.round.RoundProvider;
import com.diplomski.common.simulation.ISimulationProvider;
import com.diplomski.common.simulation.ISimulationReportProvider;
import com.diplomski.common.simulation.Simulation;
import com.diplomski.common.simulation.SimulationProvider;
import com.diplomski.common.turn.ITurnProviderFactory;
import com.diplomski.common.turn.TurnProviderFactory;

public class FunctionalTest {
	private final int NUMBER_OF_SIMULATIONS = 1;
	private final int ROUND_COUNT_LIMIT = 5;

	private List<CharacterState> initialCharacterStates;

	private ISimulationReportProvider simulationReportProvider;
	private ISimulationProvider simulationProvider;
	private IBattleProvider battleProvider;
	private IBoardStateProvider boardStateProvider;
	private IDiceFactory diceFactory;
	private ITurnProviderFactory turnProviderFactory;
	private IRoundProvider roundProvider;
	private IAttackRollOutcomeProvider attackRollOutcomeProvider;
	private IDamageProvider damageProvider;

	public void objectSetup() {

	}

	public void serviceSetup() {
		// attackRollOutcomeProvider = new AttackRollOutcomeProvider();
		// damageProvider = new DamageProvider();
		turnProviderFactory = new TurnProviderFactory(attackRollOutcomeProvider, damageProvider);
		boardStateProvider = new BoardStateProvider(turnProviderFactory, diceFactory);
		roundProvider = new RoundProvider();
		battleProvider = new BattleProvider(boardStateProvider, roundProvider);
		simulationProvider = new SimulationProvider(battleProvider);
		// simulationReportProvider = new SimulationReportProvider(simulationProvider);
	}

	@Before
	public void setup() {
		objectSetup();
		serviceSetup();
	}

	@Ignore
	@Test
	public void generateSimulationReport() {
		Simulation simulation = simulationProvider.getSimulation(initialCharacterStates, NUMBER_OF_SIMULATIONS,
				ROUND_COUNT_LIMIT);
		simulationReportProvider.getReport(simulation);
	}
}
