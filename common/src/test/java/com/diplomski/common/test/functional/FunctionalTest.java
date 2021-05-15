package com.diplomski.common.test.functional;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.diplomski.common.battle.BattleProvider;
import com.diplomski.common.battle.IBattleProvider;
import com.diplomski.common.board.BoardStateProvider;
import com.diplomski.common.board.IBoardStateProvider;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.dice.IDice;
import com.diplomski.common.round.IRoundProvider;
import com.diplomski.common.round.RoundProvider;
import com.diplomski.common.simulation.ISimulationProvider;
import com.diplomski.common.simulation.SimulationProvider;
import com.diplomski.common.turn.ITurnProviderFactory;

public class FunctionalTest {
	private final int NUMBER_OF_SIMULATIONS = 1;
	
	private List<CharacterState> initialCharacterStates;
	
	private ISimulationProvider simulationProvider;
	private IBattleProvider battleProvider;
	private IBoardStateProvider boardStateProvider;
	private IDice dice;
	private ITurnProviderFactory turnProviderFactory;
	private IRoundProvider roundProvider;
	
	public void objectSetup() {
		
	}
	
	public void mockSetup() {
		
	}
	
	public void serviceSetup() {
		turnProviderFactory = mock(ITurnProviderFactory.class);
		dice = mock(IDice.class);
		boardStateProvider = new BoardStateProvider(turnProviderFactory, dice);
		roundProvider = new RoundProvider();
		battleProvider = new BattleProvider(boardStateProvider, roundProvider);
		simulationProvider = new SimulationProvider(battleProvider);
	}
	
	@Before
	public void setup() {
		objectSetup();
		mockSetup(); // final test will not have any mocks
		serviceSetup();
	}

	@Ignore
	@Test
	public void generateSimulation() {
		simulationProvider.getSimulation(initialCharacterStates, NUMBER_OF_SIMULATIONS);
	}

}