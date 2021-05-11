package com.diplomski.common.simulation;

import java.util.ArrayList;
import java.util.List;

import com.diplomski.common.battle.Battle;
import com.diplomski.common.battle.IBattleProvider;
import com.diplomski.common.character.CharacterState;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimulationProvider implements ISimulationProvider {

	private final IBattleProvider battleProvider;

	@Override
	public Simulation getSimulation(List<CharacterState> initialCharacterState, int simulationCount) {
		List<Battle> battles = new ArrayList<>();

		// parallelize?
		for (int i = 0; i < simulationCount; i++) {
			battles.add(battleProvider.getBattle(initialCharacterState));
		}

		return Simulation.builder().battles(battles).initialCharacterStates(initialCharacterState).build();
	}

}
