package solver;

import java.util.*;

public class Day2 extends BaseSolver {
	protected Day2() {
		super(2);
	}

	private int generateSafeReports(List<String> input, boolean damper) {
		int safeReports = 0;
		int numReportsMadeSafe = 0;
		for (String report : input) {
			int[] reportArr =
				Arrays.stream(report.split(" ")).mapToInt(Integer::parseInt).toArray();
			if (isSafe(reportArr)) {
				safeReports++;
			} else if (damper) {
				if (isSafeDampened(reportArr)) {
					numReportsMadeSafe++;
				}
			}
		}
		return safeReports + numReportsMadeSafe;
	}

	private boolean isSafe(int[] reportInput) {
		if (reportInput[0] == reportInput[1]) {
			return false;
		}

		boolean increasing = reportInput[0] < reportInput[1];

		for (int i = 1; i < reportInput.length; i++) {
			if (Math.abs(reportInput[i] - reportInput[i - 1]) > 3
				    || reportInput[i] - reportInput[i - 1] == 0) {
				return false;
			}
			if (reportInput[i] < reportInput[i - 1] && increasing) {
				return false;
			}
			if (reportInput[i] > reportInput[i - 1] && !increasing) {
				return false;
			}
		}
		return true;
	}

	public boolean isSafeDampened(int[] input) {
		for (int i = 0; i < input.length; i++) {
			int[] placeholder = new int[input.length - 1];
			System.arraycopy(input, 0, placeholder, 0, i);
			if (input.length >= i + 1) {
				System.arraycopy(input, i + 1, placeholder, i, input.length - (i + 1));
			}
			if (isSafe(placeholder)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected String solvePart1(List<String> input) {
		return String.valueOf(generateSafeReports(input, false));
	}

	@Override
	protected String solvePart2(List<String> input) {
		return String.valueOf(generateSafeReports(input, true));
	}

	public static void main(String[] args) {
		new Day2();
	}
}