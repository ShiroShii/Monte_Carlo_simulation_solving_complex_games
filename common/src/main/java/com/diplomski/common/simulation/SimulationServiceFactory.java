package com.diplomski.common.simulation;

import com.diplomski.common.activity.action.attack.AttackRollOutcomeProviderFactory;
import com.diplomski.common.activity.action.attack.IAttackRollOutcomeProviderFactory;
import com.diplomski.common.battle.BattleProvider;
import com.diplomski.common.battle.IBattleProvider;
import com.diplomski.common.board.BoardStateProvider;
import com.diplomski.common.board.IBoardStateProvider;
import com.diplomski.common.board.INavigator;
import com.diplomski.common.board.NodeNavigator;
import com.diplomski.common.damage.DamageProvider;
import com.diplomski.common.damage.IDamageProvider;
import com.diplomski.common.dice.DiceFactory;
import com.diplomski.common.dice.IDiceFactory;
import com.diplomski.common.round.IRoundProvider;
import com.diplomski.common.round.RoundProvider;
import com.diplomski.common.turn.ITurnProviderFactory;
import com.diplomski.common.turn.TurnProviderFactory;

public class SimulationServiceFactory {
	public static ISimulationService getSimulationService() {
		IDiceFactory diceFactory = new DiceFactory();
		INavigator navigator = new NodeNavigator();
		IRoundProvider roundProvider = new RoundProvider();
		ISimulationReportProvider simulationReportProvider = 
				new SimulationReportProvider();
		
		IDamageProvider damageProvider = new DamageProvider(diceFactory);
		
		IAttackRollOutcomeProviderFactory attackRollOutcomeProviderFactory =
				new AttackRollOutcomeProviderFactory(diceFactory);
		
		ITurnProviderFactory turnProviderFactory = new
				TurnProviderFactory(
				attackRollOutcomeProviderFactory,
				damageProvider, 
				navigator
		);
		
		IBoardStateProvider boardStateProvider = new BoardStateProvider(
				turnProviderFactory,
				diceFactory
		);
		
		IBattleProvider battleProvider = new BattleProvider(
				boardStateProvider,
				roundProvider
		);
		
		ISimulationProvider simulationProvider = 
				new SimulationProvider(battleProvider);
		ISimulationService simulationService = new SimulationService(
				simulationProvider,
				simulationReportProvider
		);

		return simulationService;
	}
}
