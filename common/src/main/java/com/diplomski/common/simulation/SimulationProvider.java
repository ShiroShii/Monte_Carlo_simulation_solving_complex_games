package com.diplomski.common.simulation;

import java.util.List;
import java.util.stream.IntStream;

import com.diplomski.common.battle.Battle;
import com.diplomski.common.battle.IBattleProvider;
import com.diplomski.common.board.IBoard;
import com.diplomski.common.character.ICharacterState;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimulationProvider implements ISimulationProvider {

	private final IBattleProvider battleProvider;

	@Override
	public Simulation getSimulation(
			List<ICharacterState> initialCharacterState,
			IBoard board,
			int simulationCount,
			int roundCountLimit) {

		List<Battle> battles = IntStream.range(0, simulationCount)
				.parallel()
				.mapToObj(iteration -> battleProvider.getBattle(initialCharacterState, roundCountLimit, board))
				.toList();

		return Simulation.builder()
				.simulationCount(simulationCount)
				.roundCountLimit(roundCountLimit)
				.battles(battles)
				.initialCharacterStates(initialCharacterState)
				.build();
	}
}
