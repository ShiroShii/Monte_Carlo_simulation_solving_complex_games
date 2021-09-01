package com.diplomski.common.simulation;

import static com.diplomski.common.character.Party.ENEMY;
import static com.diplomski.common.character.Party.PLAYER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.diplomski.common.battle.Battle;

public class SimulationReportProvider implements ISimulationReportProvider {

	@Override
	public SimulationReport getSimulationReport(Simulation simulation) {
		HashMap<Integer, Float> winRateConvergence = getWinConvergence(simulation);
		HashMap<Integer, Float> drawRateConvergence = getDrawRateConvergence(simulation);
		HashMap<Pair<Integer, Integer>, Integer> outcomes = new HashMap<>();

		for (Battle battle : simulation.getBattles()) {
			Integer playerActiveCount = battle.getFinalBoardState().getPartyActiveCount(PLAYER);
			Integer enemyActiveCount = battle.getFinalBoardState().getPartyActiveCount(ENEMY);
			Pair<Integer, Integer> outcome = new Pair<>(playerActiveCount, enemyActiveCount);

			int count = outcomes.containsKey(outcome) ? outcomes.get(outcome) : 0;
			outcomes.put(outcome, count + 1);
		}

		List<Pair<Pair<Integer, Integer>, Integer>> orderedOutcomes = outcomes.entrySet().stream()
				.sorted((x, y) -> x.getKey().get_1() > y.getKey().get_1()
						|| (x.getKey().get_1() == y.getKey().get_1() && x.getKey().get_2() < y.getKey().get_2()) ? -1
								: 1)
				.map(entry -> new Pair<Pair<Integer, Integer>, Integer>(entry.getKey(), entry.getValue())).toList();

		int winCount = (int) simulation.getBattles().stream()
				.filter(x -> x.isBattleComplete() && x.getWinningParty().get().equals(PLAYER)).count();
		int lossCount = (int) simulation.getBattles().stream()
				.filter(x -> x.isBattleComplete() && x.getWinningParty().get().equals(ENEMY)).count();
		int drawCount = (int) simulation.getBattles().stream().filter(x -> !x.isBattleComplete()).count();

		return SimulationReport.builder().winRateConvergence(winRateConvergence).drawRateConvergence(drawRateConvergence)
				.simulationCount(simulation.getSimulationCount()).roundCountLimit(simulation.getRoundCountLimit())
				.winCount(winCount).lossCount(lossCount).drawCount(drawCount).outcomes(orderedOutcomes).build();
	}

	private HashMap<Integer, Float> getDrawRateConvergence(Simulation simulation) {
		HashMap<Integer, Float> drawRateConvergence = new HashMap<>();
		int draws = 0;
		for (int i = 0; i < simulation.getBattles().size(); i++) {
			if (!simulation.getBattles().get(i).isBattleComplete()) {
				draws++;
			}
			drawRateConvergence.put(i + 1, (float) draws / (i + 1));
		}
		return drawRateConvergence;
	}

	private HashMap<Integer, Float> getWinConvergence(Simulation simulation) {
		HashMap<Integer, Float> winRateConvergence = new HashMap<>();
		
		int battlesWon = 0;
		for (int i = 0; i < simulation.getBattles().size(); i++) {
			Battle battle = simulation.getBattles().get(i);
			if (battle.isBattleComplete() && battle.getWinningParty().get().equals(PLAYER)) {
				battlesWon++;
			}
			winRateConvergence.put(i + 1, (float) battlesWon / (i + 1));
		}

		return winRateConvergence;
	}
	
	private List<Battle> getWonBattlesNoCasualties(Simulation simulation) {
		List<Battle> wonBattles = new ArrayList<>();
		
		for (Battle battle : simulation.getBattles()) {
			if (battle.isBattleComplete() && battle.getWinningParty().get().equals(PLAYER)) {
				wonBattles.add(battle);
			}
		}

		return wonBattles;
	}
}
