package solver;

import common.*;

import java.util.*;

public class Day2 {
	private List<Integer[]> reports = new ArrayList<>();
	private List<Integer[]> unsafeReports = new ArrayList<>();
	private List<Integer[]> safeReports = new ArrayList<>();

	public void loadReports() {
		reports.clear();
		unsafeReports.clear();
		safeReports.clear();
		String input = Utils.loadFileAsString(2);

		try (Scanner scan = new Scanner(input)) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] parts = line.split("\\s");
				Integer[] intParts = new Integer[parts.length];
				for (int i = 0; i < parts.length; i++) {
					intParts[i] = Integer.parseInt(parts[i]);
				}
				reports.add(intParts);
			}
		}
	}

	public int solvePart1() {
		loadReports();
		for (Integer[] report : reports) {
			if (isSafe(report)) {
				safeReports.add(report);
			}
		}
		return safeReports.size();
	}

	public boolean isSafe(Integer[] input) {
		if (Objects.equals(input[0], input[1])) {
			return false;
		}
		boolean increasing = input[0] < input[1];
		for (int i = 1; i < input.length; i++) {
			if (Math.abs(input[i] - input[i - 1]) > 3
			    || input[i] - input[i - 1] == 0) {
				return false;
			}
			if (input[i] < input[i - 1] && increasing) {
				return false;
			}
			if (input[i] > input[i - 1] && !increasing) {
				return false;
			}

		}
		return true;
	}

	public int solvePart2() {
		loadReports();
		int reportsMadeSafe = 0;
		for (Integer[] report : reports) {
			if (isSafe(report)) {
				safeReports.add(report);
			} else if (madeReportSafe(report)){
				reportsMadeSafe += 1;
			}
		}
		return safeReports.size() + reportsMadeSafe;
	}

	public boolean madeReportSafe(Integer[] input) {
		for (int i = 0; i < input.length; i++) {
			Integer[] placeholder = new Integer[input.length - 1];
			System.arraycopy(input, 0, placeholder, 0, i);
			if (input.length >= i + 1) {
				System.arraycopy(input, i + 1, placeholder, i,
					input.length - (i + 1));
			}

			if (isSafe(placeholder)) {
				return true;
			}
		}
		return false;
	}
}
