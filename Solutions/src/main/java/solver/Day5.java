package solver;

import java.util.*;
import java.util.stream.*;

public class Day5 extends BaseSolver {

	private List<String> parseInputForRules(List<String> input){
		List<String> ruleList = new ArrayList<>();
		for (String line : input) {
			if (line.isBlank()) {
				break;
			} else {
				ruleList.add(line);
			}
		}
		return ruleList;
	}

	private Map<Integer, List<Integer>> collectOrderingRules(List<String> input) {
		Map<Integer, List<Integer>> retMap = new HashMap<>();
		for (String rule : input) {
			int key = Integer.parseInt(rule.split("\\|")[0]);
			int value = Integer.parseInt(rule.split("\\|")[1]);
			retMap.putIfAbsent(key, new ArrayList<>());
			retMap.get(key).add(value);
		}
		return retMap;
	}

	private List<String> getUpdates(List<String> input) {
		List<String> printOrders = new ArrayList<>();
		int i = 0;
		while (!input.get(i).isBlank()) {
			i++;
		}
		i++;
		for (; i < input.size(); i++) {
			printOrders.add(input.get(i));
		}
		return printOrders;
	}

	private boolean isBefore(int val1, int val2, Map<Integer, List<Integer>> rules) {
		return rules.containsKey(val1) && rules.get(val1).contains(val2);
	}

	private boolean isValid(List<Integer> update, Map<Integer, List<Integer>> rules) {
		for (int i = 0; i < update.size() - 1; i++) {
			for (int j = i + 1; j < update.size(); j++) {
				if (!isBefore(update.get(i), update.get(j), rules)) {
					return false;
				}
			}
		}
		return true;
	}

	private List<List<Integer>> getValidUpdates(List<String> input,
	                                     Map<Integer, List<Integer>> ruleset) {
		List<List<Integer>> validUpdates = new ArrayList<>();
		for (String update : input) {
			List<Integer> interrogatee =
				Arrays.stream(update.split(",")).map(Integer::valueOf).toList();
			if (isValid(interrogatee, ruleset)) {
				validUpdates.add(interrogatee);
			}
		}
		return validUpdates;
	}

	private List<List<Integer>> getInvalidUpdates(List<String> input,
	                                              Map<Integer, List<Integer>> ruleset) {
		List<List<Integer>> invalidUpdates = new ArrayList<>();
		for (String update : input) {
			List<Integer> interrogatee =
				Arrays.stream(update.split(",")).map(Integer::valueOf).toList();
			if (!isValid(interrogatee, ruleset)) {
				invalidUpdates.add(interrogatee);
			}
		}
		return invalidUpdates;
	}

	private List<Integer> fixInvalidUpdate(List<Integer> invalidUpdate,
	                                       Map<Integer, List<Integer>> ruleset) {
		ArrayList<Integer> fixed = new ArrayList<>(invalidUpdate);
		for (int i = 0; i < invalidUpdate.size() - 1; i++) {
			final int val1 = invalidUpdate.get(i);
			for (int j = i + 1; j < invalidUpdate.size(); j++) {
				final int val2 = invalidUpdate.get(j);
				if (!isBefore(val1, val2, ruleset)) {
					fixed.set(i, val2);
					fixed.set(j, val1);
					return fixed;
				}
			}
		}
		return fixed;
	}

	private List<List<Integer>> fixUpdateList(List<List<Integer>> invalidUpdates,
	                                          Map<Integer, List<Integer>> ruleset) {
		List<List<Integer>> listOfFixedUpdates = new ArrayList<>();
		for (List<Integer> update : invalidUpdates) {
			while (!isValid(update, ruleset)) {
				update = fixInvalidUpdate(update, ruleset);
			}
			listOfFixedUpdates.add(update);
		}
		return listOfFixedUpdates;
	}


	protected Day5() {
		super(5);
	}

	@Override
	protected String solvePart1(List<String> input) {
		List<String> rules = parseInputForRules(input);
		List<String> updates = getUpdates(input);
		Map<Integer, List<Integer>> ruleset = collectOrderingRules(rules);
		return String.valueOf(getValidUpdates(updates, ruleset).stream().mapToInt(v -> v.get(v.size() / 2)).sum());

	}

	@Override
	protected String solvePart2(List<String> input) {
		List<String> rules = parseInputForRules(input);
		List<String> updates = getUpdates(input);
		Map<Integer, List<Integer>> ruleset = collectOrderingRules(rules);
		List<List<Integer>> invalids = getInvalidUpdates(updates, ruleset);
		List<List<Integer>> fixedUpdates = fixUpdateList(invalids, ruleset);
		return String.valueOf(fixedUpdates.stream().mapToInt(v -> v.get(v.size() / 2)).sum());
	}

	public static void main(String[] args) {
		new Day5();
	}
}
