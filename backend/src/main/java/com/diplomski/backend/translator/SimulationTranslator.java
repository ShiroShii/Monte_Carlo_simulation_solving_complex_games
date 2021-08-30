package com.diplomski.backend.translator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.diplomski.backend.contract.BattleOutcomeConvergence;
import com.diplomski.backend.contract.PlayerCharacterStateResponse;
import com.diplomski.backend.contract.SimulationResponse;
import com.diplomski.backend.dal.PlayerCharacterStateDbModel;
import com.diplomski.common.simulation.SimulationReport;

public class SimulationTranslator {
	public static PlayerCharacterStateResponse translate(PlayerCharacterStateDbModel input) {
		return PlayerCharacterStateResponse.builder().id(input.getId())
				.playerCharacterId(input.getPlayerCharacter().getId()).currentHp(input.getCurrentHp())
				.targetingStyle(input.getTargetingStyle()).playStyle(input.getPlayStyle())
				.tileId(input.getNodeTile().getId()).build();
	}

	public static SimulationResponse translate(SimulationReport input, UUID battleId) {
		List<BattleOutcomeConvergence> battleOutcomeConvergence = new ArrayList<>();

		for (int i = 1; i <= input.getSimulationCount(); i++) {
			battleOutcomeConvergence
					.add(BattleOutcomeConvergence.builder().count(i).drawRate(input.getDrawRateConvergence().get(i))
							.winRate(input.getPlayerPartyReport().getWinRateConvergence().get(i)).build());
		}

		return SimulationResponse.builder().battleId(battleId).roundCountLimit(input.getRoundCountLimit())
				.simulationCount(input.getSimulationCount()).battleOutcomeConvergence(battleOutcomeConvergence).build();
	}
}
