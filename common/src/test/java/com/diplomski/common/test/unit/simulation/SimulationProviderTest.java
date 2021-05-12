package com.diplomski.common.test.unit.simulation;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.diplomski.common.battle.Battle;
import com.diplomski.common.battle.IBattleProvider;
import com.diplomski.common.character.CharacterState;
import com.diplomski.common.simulation.Simulation;
import com.diplomski.common.simulation.SimulationProvider;

public class SimulationProviderTest {
	private IBattleProvider battleProviderMock = mock(IBattleProvider.class);
	private int expectedBattleCount = 3;
	private Battle battle1;
	private Battle battle2;
	private Battle battle3;

	private List<Battle> battles;

	private List<CharacterState> characterStates;
	private Simulation expectedSimulation;
	private SimulationProvider unitUnderTest;

	@Before
	public void setup() {
		battle1 = Battle.builder().build();
		battle2 = Battle.builder().build();
		battle3 = Battle.builder().build();

		battles = Arrays.asList(battle1, battle2, battle3);

		characterStates = new ArrayList<>();

		expectedSimulation = Simulation.builder().battles(battles).initialCharacterStates(characterStates).build();

		when(battleProviderMock.getBattle(eq(characterStates))).thenReturn(battle1).thenReturn(battle2)
				.thenReturn(battle3);
		unitUnderTest = new SimulationProvider(battleProviderMock);
	}

	@Test
	public void getSimulation() {
		Simulation result = unitUnderTest.getSimulation(characterStates, expectedBattleCount);
		assertEquals(expectedSimulation, result);
		verify(battleProviderMock, times(3)).getBattle(eq(characterStates));
	}

}
