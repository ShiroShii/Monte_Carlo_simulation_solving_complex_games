package com.diplomski.common.simulation;

import java.util.Arrays;
import java.util.List;

public class TestStat {

	public static void main(String[] args) {
		List<Integer> healthList = Arrays.asList(1, 2, 3, 4, 5, 6, 7 );

		float median = (healthList.size() % 2 == 0)
				? ((float) healthList.get(healthList.size() / 2) + (float) healthList.get(healthList.size() / 2 - 1))
						/ 2
				: (float) healthList.get(healthList.size() / 2);

		List<Integer> lower = healthList.subList(0, (healthList.size() % 2 == 0) ? (healthList.size() / 2) : healthList.size() / 2 + 1);

		List<Integer> upper = healthList.subList(healthList.size() / 2, healthList.size());

		float lowerQuantile = (lower.size() % 2 == 0)
				? ((float) lower.get(lower.size() / 2) + (float) lower.get(lower.size() / 2 - 1)) / 2
				: (float) lower.get(lower.size() / 2);

		float upperQuantile = (upper.size() % 2 == 0)
				? ((float) upper.get(upper.size() / 2) + (float) upper.get(upper.size() / 2 - 1)) / 2
				: (float) upper.get(upper.size() / 2);

		System.out.println(lower);
		System.out.println(upper);
		System.out.println(lowerQuantile + " " + median + " " + upperQuantile);
	}

}
