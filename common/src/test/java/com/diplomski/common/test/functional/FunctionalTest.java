package com.diplomski.common.test.functional;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.diplomski.common.activity.AttackRollModifierProvider;
import com.diplomski.common.activity.AttackRollOutcomeProvider;
import com.diplomski.common.activity.IArmorClassProvider;
import com.diplomski.common.activity.IAttackRollModifierProvider;
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

import lombok.NonNull;

public class FunctionalTest {
	private final int NUMBER_OF_SIMULATIONS = 1;
	private final int ROUND_COUNT_LIMIT = 5;

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

	}

	public void serviceSetup() {
		// damageProvider = new DamageProvider();
		// armorClassProvider = new ArmorClassProvider();
		attackRoleModifierProvider = new AttackRollModifierProvider();
		attackRollOutcomeProvider = new AttackRollOutcomeProvider(attackRoleModifierProvider, armorClassProvider, diceFactory);
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
		Simulation simulation = simulationProvider
				.getSimulation(initialCharacterStates, NUMBER_OF_SIMULATIONS, ROUND_COUNT_LIMIT);
		simulationReportProvider.getReport(simulation);
	}
}
