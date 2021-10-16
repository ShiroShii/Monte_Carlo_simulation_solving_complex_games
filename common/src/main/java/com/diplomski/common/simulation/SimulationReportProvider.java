package com.diplomski.common.simulation;

import static com.diplomski.common.character.Party.ENEMY;
import static com.diplomski.common.character.Party.PLAYER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.diplomski.common.activity.action.attack.AttackActionActivity;
import com.diplomski.common.battle.Battle;
import com.diplomski.common.character.PlayerCharacterState;

public class SimulationReportProvider implements ISimulationReportProvider {

	@Override
	public SimulationReport getSimulationReport(Simulation simulation) {
		int initialPlayerCount = (int) simulation.getInitialCharacterStates().stream()
				.filter(x -> x.getParty().equals(PLAYER)).count();

		int winCount = (int) simulation.getBattles().stream()
				.filter(x -> x.isBattleComplete() && x.getWinningParty().get().equals(PLAYER))
				.count();
		int lossCount = (int) simulation.getBattles().stream()
				.filter(x -> x.isBattleComplete() && x.getWinningParty().get().equals(ENEMY))
				.count();
		int drawCount = (int) simulation.getBattles().stream()
				.filter(x -> !x.isBattleComplete())
				.count();

		PlayerWinStateReport playerWinStateReport = PlayerWinStateReport.builder()
				.health(getPlayerHealthStatReport(getWonBattles(simulation)))
				.damageDealt(getPlayerDamageDealtReport(simulation))
				.damageTaken(getPlayerDamageTakenReport(simulation))
				.build();

		return SimulationReport.builder()
				.winRateConvergence(getWinConvergence(simulation))
				.drawRateConvergence(getDrawConvergence(simulation))
				.lossRateConvergence(getLossConvergence(simulation))
				.winCount(winCount)
				.drawCount(drawCount)
				.lossCount(lossCount)
				.outcomes(getOutcomes(simulation))
				.downedPlayers(getDownedPlayers(simulation, initialPlayerCount))
				.playerWinStateReport(playerWinStateReport)
				.playerReports(getPlayerReports(simulation))
				.simulationCount(simulation.getSimulationCount())
				.roundCountLimit(simulation.getRoundCountLimit())
				.initialPlayerCount(initialPlayerCount)
				.build();
	}

	private List<PlayerReport> getPlayerReports(Simulation simulation) {
		return simulation.getInitialCharacterStates()
				.stream()
				.filter(x -> x.getParty().equals(PLAYER))
				.map(x -> PlayerReport.builder()
						.id(x.getId())
						.name(((PlayerCharacterState) x).getName())
						.downCount((int) simulation.getBattles()
								.stream()
								.filter(y -> y.getFinalBoardState().getCharacterStates().get(x.getId()).getCurrentHp()
										== 0)
								.count())
						.playerBoxPlot(PlayerWinStateReport.builder()
								.health(getPlayerHealthStatReport(getWonBattles(simulation), x.getId()))
								.damageDealt(getPlayerDamageDealtReport(simulation, x.getId()))
								.damageTaken(getPlayerDamageTakenReport(simulation, x.getId()))
								.build())
						.build())
				.toList();
	}

	private List<Pair<Pair<Integer, Integer>, Integer>> getOutcomes(Simulation simulation) {
		HashMap<Pair<Integer, Integer>, Integer> outcomes = new HashMap<>();

		for (Battle battle : simulation.getBattles()) {
			Integer playerActiveCount = battle.getFinalBoardState().getPartyActiveCount(PLAYER);
			Integer enemyActiveCount = battle.getFinalBoardState().getPartyActiveCount(ENEMY);
			Pair<Integer, Integer> outcome = new Pair<>(playerActiveCount, enemyActiveCount);

			int count = outcomes.containsKey(outcome) ? outcomes.get(outcome) : 0;
			outcomes.put(outcome, count + 1);
		}

		return outcomes.entrySet().stream()
				.sorted((x, y) -> x.getKey().get_1() > y.getKey().get_1()
						|| (x.getKey().get_1() == y.getKey().get_1() && x.getKey().get_2() < y.getKey().get_2()) ? -1
								: 1)
				.map(entry -> new Pair<Pair<Integer, Integer>, Integer>(entry.getKey(), entry.getValue())).toList();
	}

	private List<Pair<Integer, Integer>> getDownedPlayers(Simulation simulation, int initialPlayerCount) {
		HashMap<Integer, Integer> downedPlayers = new HashMap<>();

		for (Battle battle : simulation.getBattles()) {
			Integer downedPlayerCount = initialPlayerCount - battle.getFinalBoardState().getPartyActiveCount(PLAYER);

			int count = downedPlayers.containsKey(downedPlayerCount) ? downedPlayers.get(downedPlayerCount) : 0;
			downedPlayers.put(downedPlayerCount, count + 1);
		}

		return downedPlayers.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.map(entry -> new Pair<>(entry.getKey(), entry.getValue())).toList();
	}

	private HashMap<Integer, Float> getDrawConvergence(Simulation simulation) {
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

	private HashMap<Integer, Float> getLossConvergence(Simulation simulation) {
		HashMap<Integer, Float> lossConvergence = new HashMap<>();

		int battlesLost = 0;
		for (int i = 0; i < simulation.getBattles().size(); i++) {
			Battle battle = simulation.getBattles().get(i);
			if (battle.isBattleComplete() && battle.getWinningParty().get().equals(ENEMY)) {
				battlesLost++;
			}
			lossConvergence.put(i + 1, (float) battlesLost / (i + 1));
		}

		return lossConvergence;
	}

	private List<Battle> getWonBattles(Simulation simulation) {
		List<Battle> wonBattles = new ArrayList<>();

		for (Battle battle : simulation.getBattles()) {
			if (battle.isBattleComplete() && battle.getWinningParty().get().equals(PLAYER)) {
				wonBattles.add(battle);
			}
		}

		return wonBattles;
	}

	private StatReport getPlayerHealthStatReport(List<Battle> battles) {
		return getPlayerHealthStatReport(battles, null);
	}

	private StatReport getPlayerHealthStatReport(List<Battle> battles, UUID playerId) {
		List<Integer> healthList = battles.stream().map(x -> x.getFinalBoardState())
				.flatMap(x -> x.getCharacterStates().values().stream())
				.filter(x -> x.getParty().equals(PLAYER))
				.filter(playerId != null ? x -> x.getId().equals(playerId) : x -> true)
				.map(x -> x.getCurrentHp())
				.filter(x -> x > 0).sorted().toList();

		if (healthList.size() == 0) {
			return StatReport.builder()
					.min(0)
					.lowerQuantile(0)
					.median(0)
					.upperQuantile(0)
					.max(0)
					.build();
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
		return getPlayerDamageTakenReport(simulation, null);
	}

	private StatReport getPlayerDamageTakenReport(Simulation simulation, UUID playerId) {
		List<Integer> damageTakenList = simulation.getBattles().stream()
				.flatMap(battle -> battle.getRounds().stream()
						.flatMap(round -> round.getTurns().stream().flatMap(turn -> turn.getActivities().stream()
								.filter(activity -> activity instanceof AttackActionActivity)
								.map(activity -> (AttackActionActivity) activity)
								.filter(x -> simulation.getInitialCharacterStates().stream()
										.filter(y -> y.getId().equals(x.getTargetId())).findFirst().get().getParty()
										.equals(PLAYER))
								.filter(playerId != null
										? x -> x.getTargetId().equals(playerId)
										: x -> true)
								.collect(Collectors
										.groupingBy(x -> x.getTargetId(), Collectors.summingInt(x -> x.getDamage())))
								.entrySet().stream())
								.collect(Collectors
										.groupingBy(entry -> entry.getKey(), Collectors.summingInt(x -> x.getValue())))
								.entrySet().stream())
						.collect(Collectors
								.groupingBy(entry -> entry.getKey(), Collectors.summingInt(x -> x.getValue())))
						.entrySet().stream())
				.map(entry -> entry.getValue()).sorted().toList();

		if (damageTakenList.size() == 0) {
			return StatReport.builder()
					.min(0)
					.lowerQuantile(0)
					.median(0)
					.upperQuantile(0)
					.max(0)
					.build();
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

	private StatReport getPlayerDamageDealtReport(Simulation simulation) {
		return getPlayerDamageDealtReport(simulation, null);
	}

	private StatReport getPlayerDamageDealtReport(Simulation simulation, UUID playerId) {
		List<Integer> damageDealtList = simulation.getBattles().stream()
				.flatMap(battle -> battle.getRounds().stream()
						.flatMap(round -> round.getTurns().stream()
								.flatMap(turn -> turn.getActivities().stream()
										.filter(activity -> activity instanceof AttackActionActivity)
										.map(activity -> (AttackActionActivity) activity)
										.filter(x -> simulation.getInitialCharacterStates()
												.stream()
												.filter(y -> y.getId().equals(x.getInitiatorId()))
												.findFirst()
												.get()
												.getParty()
												.equals(PLAYER))
										.filter(playerId != null
												? x -> x.getInitiatorId().equals(playerId)
												: x -> true)
										.collect(Collectors.groupingBy(x -> x.getInitiatorId(), Collectors
												.summingInt(x -> x.getDamage())))
										.entrySet().stream())
								.collect(Collectors
										.groupingBy(entry -> entry.getKey(), Collectors.summingInt(x -> x.getValue())))
								.entrySet().stream())
						.collect(Collectors
								.groupingBy(entry -> entry.getKey(), Collectors.summingInt(x -> x.getValue())))
						.entrySet().stream())
				.map(entry -> entry.getValue()).sorted().toList();

		if (damageDealtList.size() == 0) {
			return StatReport.builder()
					.min(0)
					.lowerQuantile(0)
					.median(0)
					.upperQuantile(0)
					.max(0)
					.build();
		}

		int min = damageDealtList.get(0);
		int max = damageDealtList.get(damageDealtList.size() - 1);

		float median = (damageDealtList.size() % 2 == 0)
				? ((float) damageDealtList.get(damageDealtList.size() / 2)
						+ (float) damageDealtList.get(damageDealtList.size() / 2 - 1)) / 2
				: (float) damageDealtList.get(damageDealtList.size() / 2);

		List<Integer> lower = damageDealtList
				.subList(0, (damageDealtList.size() % 2 == 0) ? (damageDealtList.size() / 2)
						: damageDealtList.size() / 2 + 1);

		List<Integer> upper = damageDealtList.subList(damageDealtList.size() / 2, damageDealtList.size());

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
