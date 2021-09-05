package com.diplomski.common.simulation;

import static com.diplomski.common.character.Party.ENEMY;
import static com.diplomski.common.character.Party.PLAYER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.diplomski.common.activity.AttackActionActivity;
import com.diplomski.common.battle.Battle;

public class SimulationReportProvider implements ISimulationReportProvider {

	@Override
	public SimulationReport getSimulationReport(Simulation simulation) {
		HashMap<Integer, Float> winRateConvergence = getWinConvergence(simulation);
		HashMap<Integer, Float> drawRateConvergence = getDrawRateConvergence(simulation);
		HashMap<Pair<Integer, Integer>, Integer> outcomes = new HashMap<>();
		HashMap<Integer, Integer> downedPlayers = new HashMap<>();

		int initialPlayerCount = (int) simulation.getInitialCharacterStates().stream()
				.filter(x -> x.getParty().equals(PLAYER)).count();

		for (Battle battle : simulation.getBattles()) {
			Integer downedPlayerCount = initialPlayerCount - battle.getFinalBoardState().getPartyActiveCount(PLAYER);

			int count = downedPlayers.containsKey(downedPlayerCount) ? downedPlayers.get(downedPlayerCount) : 0;
			downedPlayers.put(downedPlayerCount, count + 1);
		}

		List<Pair<Integer, Integer>> orderedDownedPlayers = downedPlayers.entrySet().stream()
				.sorted(Map.Entry.comparingByKey()).map(entry -> new Pair<>(entry.getKey(), entry.getValue())).toList();

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

		StatReport healthStatReport = getPlayerHealthStatReport(getWonBattlesNoCasualties(simulation));
		StatReport damageDeltReport = getPlayerDamageDeltReport(simulation);
		StatReport damageTakenReport = getPlayerDamageTakenReport(simulation);

		PlayerWinStateReport playerWinStateReport = PlayerWinStateReport.builder().health(healthStatReport)
				.damageDelt(damageDeltReport).damageTaken(damageTakenReport).build();

		return SimulationReport.builder().winRateConvergence(winRateConvergence)
				.drawRateConvergence(drawRateConvergence).simulationCount(simulation.getSimulationCount())
				.roundCountLimit(simulation.getRoundCountLimit()).winCount(winCount).lossCount(lossCount)
				.downedPlayers(orderedDownedPlayers).drawCount(drawCount).outcomes(orderedOutcomes)
				.playerWinStateReport(playerWinStateReport).initialPlayerCount(initialPlayerCount).build();
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

	private StatReport getPlayerHealthStatReport(List<Battle> battles) {
		List<Integer> healthList = battles.stream().map(x -> x.getFinalBoardState())
				.flatMap(x -> x.getCharacterStates().values().stream()).filter(x -> x.getParty().equals(PLAYER))
				.map(x -> x.getCurrentHp()).filter(x -> x > 0).sorted().toList();

		if (healthList.size() == 0) {
			return null;
		}

		int min = healthList.get(0);
		int max = healthList.get(healthList.size() - 1);

		float median = (healthList.size() % 2 == 0)
				? ((float) healthList.get(healthList.size() / 2) + (float) healthList.get(healthList.size() / 2 - 1))
						/ 2
				: (float) healthList.get(healthList.size() / 2);

		List<Integer> lower = healthList
				.subList(0, (healthList.size() % 2 == 0) ? (healthList.size() / 2) : healthList.size() / 2 + 1);

		List<Integer> upper = healthList.subList(healthList.size() / 2, healthList.size());

		float lowerQuantile = (lower.size() % 2 == 0)
				? ((float) lower.get(lower.size() / 2) + (float) lower.get(lower.size() / 2 - 1)) / 2
				: (float) lower.get(lower.size() / 2);

		float upperQuantile = (upper.size() % 2 == 0)
				? ((float) upper.get(upper.size() / 2) + (float) upper.get(upper.size() / 2 - 1)) / 2
				: (float) upper.get(upper.size() / 2);

		return StatReport.builder().min(min).lowerQuantile(lowerQuantile).median(median).upperQuantile(upperQuantile)
				.max(max).build();
	}

	private StatReport getPlayerDamageTakenReport(Simulation simulation) {
		List<Integer> damageTakenList = simulation.getBattles().stream().flatMap(battle -> battle.getRounds().stream()
				.flatMap(round -> round.getTurns().stream().flatMap(turn -> turn.getActivities().stream()
						.filter(activity -> activity instanceof AttackActionActivity)
						.map(activity -> (AttackActionActivity) activity)
						.filter(x -> simulation.getInitialCharacterStates().stream()
								.filter(y -> y.getId().equals(x.getTargetId())).findFirst().get().getParty()
								.equals(PLAYER))
						.collect(Collectors.groupingBy(x -> x.getTargetId(), Collectors.summingInt(x -> x.getDamage())))
						.entrySet().stream())
						.collect(Collectors
								.groupingBy(entry -> entry.getKey(), Collectors.summingInt(x -> x.getValue())))
						.entrySet().stream())
				.collect(Collectors.groupingBy(entry -> entry.getKey(), Collectors.summingInt(x -> x.getValue())))
				.entrySet().stream()).map(entry -> entry.getValue()).sorted().toList();

		if (damageTakenList.size() == 0) {
			return null;
		}

		int min = damageTakenList.get(0);
		int max = damageTakenList.get(damageTakenList.size() - 1);

		float median = (damageTakenList.size() % 2 == 0)
				? ((float) damageTakenList.get(damageTakenList.size() / 2)
						+ (float) damageTakenList.get(damageTakenList.size() / 2 - 1)) / 2
				: (float) damageTakenList.get(damageTakenList.size() / 2);

		List<Integer> lower = damageTakenList
				.subList(0, (damageTakenList.size() % 2 == 0) ? (damageTakenList.size() / 2)
						: damageTakenList.size() / 2 + 1);

		List<Integer> upper = damageTakenList.subList(damageTakenList.size() / 2, damageTakenList.size());

		float lowerQuantile = (lower.size() % 2 == 0)
				? ((float) lower.get(lower.size() / 2) + (float) lower.get(lower.size() / 2 - 1)) / 2
				: (float) lower.get(lower.size() / 2);

		float upperQuantile = (upper.size() % 2 == 0)
				? ((float) upper.get(upper.size() / 2) + (float) upper.get(upper.size() / 2 - 1)) / 2
				: (float) upper.get(upper.size() / 2);

		return StatReport.builder().min(min).lowerQuantile(lowerQuantile).median(median).upperQuantile(upperQuantile)
				.max(max).build();
	}

	private StatReport getPlayerDamageDeltReport(Simulation simulation) {
		List<Integer> damageDeltList = simulation.getBattles().stream()
				.flatMap(battle -> battle.getRounds().stream()
						.flatMap(round -> round.getTurns().stream().flatMap(turn -> turn.getActivities().stream()
								.filter(activity -> activity instanceof AttackActionActivity)
								.map(activity -> (AttackActionActivity) activity)
								.filter(x -> simulation.getInitialCharacterStates().stream()
										.filter(y -> y.getId().equals(x.getInitiatorId())).findFirst().get().getParty()
										.equals(PLAYER))
								.collect(Collectors
										.groupingBy(x -> x.getInitiatorId(), Collectors.summingInt(x -> x.getDamage())))
								.entrySet().stream())
								.collect(Collectors
										.groupingBy(entry -> entry.getKey(), Collectors.summingInt(x -> x.getValue())))
								.entrySet().stream())
						.collect(Collectors
								.groupingBy(entry -> entry.getKey(), Collectors.summingInt(x -> x.getValue())))
						.entrySet().stream())
				.map(entry -> entry.getValue()).sorted().toList();

		if (damageDeltList.size() == 0) {
			return null;
		}

		int min = damageDeltList.get(0);
		int max = damageDeltList.get(damageDeltList.size() - 1);

		float median = (damageDeltList.size() % 2 == 0)
				? ((float) damageDeltList.get(damageDeltList.size() / 2)
						+ (float) damageDeltList.get(damageDeltList.size() / 2 - 1)) / 2
				: (float) damageDeltList.get(damageDeltList.size() / 2);

		List<Integer> lower = damageDeltList.subList(0, (damageDeltList.size() % 2 == 0) ? (damageDeltList.size() / 2)
				: damageDeltList.size() / 2 + 1);

		List<Integer> upper = damageDeltList.subList(damageDeltList.size() / 2, damageDeltList.size());

		float lowerQuantile = (lower.size() % 2 == 0)
				? ((float) lower.get(lower.size() / 2) + (float) lower.get(lower.size() / 2 - 1)) / 2
				: (float) lower.get(lower.size() / 2);

		float upperQuantile = (upper.size() % 2 == 0)
				? ((float) upper.get(upper.size() / 2) + (float) upper.get(upper.size() / 2 - 1)) / 2
				: (float) upper.get(upper.size() / 2);

		return StatReport.builder().min(min).lowerQuantile(lowerQuantile).median(median).upperQuantile(upperQuantile)
				.max(max).build();
	}
}
