package com.diplomski.common.simulation;

import java.util.List;

import com.diplomski.common.board.IBoard;
import com.diplomski.common.character.ICharacterState;

public interface ISimulationProvider {
	public Simulation getSimulation(
			List<ICharacterState> initialCharacterState,
			IBoard board,
			int simulationCount,
			int roundCountLimit);
}
