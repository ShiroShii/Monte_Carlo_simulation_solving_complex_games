package com.diplomski.common.simulation;

import java.util.ArrayList;
import java.util.List;

import com.diplomski.common.battle.Battle;
import com.diplomski.common.battle.IBattleProvider;
import com.diplomski.common.character.ICharacterState;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimulationProvider implements ISimulationProvider {

	private final IBattleProvider battleProvider;

	@Override
	public Simulation getSimulation(
			List<ICharacterState> initialCharacterState,
			int simulationCount,
			int roundCountLimit) {
		List<Battle> battles = new ArrayList<>();

		// parallelize?
		for (int i = 0; i < simulationCount; i++) {
			battles.add(battleProvider.getBattle(initialCharacterState, roundCountLimit));
		}

		return Simulation.builder().simulationCount(simulationCount)
				.roundCountLimit(roundCountLimit).battles(battles).initialCharacterStates(initialCharacterState).build();
	}

}
