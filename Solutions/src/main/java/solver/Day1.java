package solver;

import common.*;

import java.io.*;
import java.util.*;

public class Day1 extends BaseSolver{
	private List<Integer> leftList;
	private List<Integer> rightList;


	public Day1() {
		super(1);
	}


	public int getDistance(List<String> input) {
		regenerateLists();
		splitList(input);
		int totalDistance = 0;
		leftList.sort(Integer::compareTo);
		rightList.sort(Integer::compareTo);

		for (int i = 0; i < leftList.size(); i++) {
			int leftNum = leftList.get(i);
			int rightNum = rightList.get(i);
			totalDistance += Math.abs(leftNum - rightNum);
		}
		return totalDistance;
	}

	public int getSimilarity(List<String> input) {
		regenerateLists();
		splitList(input);
		int similarityScore = 0;
		Map<Integer, Integer> occurrencesRight = new HashMap<>();
		for (Integer val : rightList) {
			occurrencesRight.putIfAbsent(val, 0);
			occurrencesRight.put(val, occurrencesRight.get(val) + 1);
		}
		for (Integer val : leftList) {
			similarityScore += val * occurrencesRight.getOrDefault(val, 0);
		}
		return similarityScore;
	}

	private void splitList(List<String> input) {
		for (String s : input) {
			String[] temp = s.split(" {3}");
			leftList.add(Integer.valueOf(temp[0]));
			rightList.add(Integer.valueOf(temp[1]));
		}
	}

	private void regenerateLists(){
		if (leftList == null) {
			leftList = new ArrayList<>();
		} else {
			leftList.clear();
		}

		if (rightList == null) {
			rightList = new ArrayList<>();
		} else {
			rightList.clear();
		}
	}

	@Override
	protected String solvePart1(List<String> input) {
		return String.valueOf(getDistance(input));
	}

	@Override
	protected String solvePart2(List<String> input) {
		return String.valueOf(getSimilarity(input));
	}

	public static void main(String[] args) {
		new Day1();
	}
}
