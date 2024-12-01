package solver;

import common.*;

import java.io.*;
import java.util.*;

public class Day1 {
	private final List<Integer> leftList;
	private final List<Integer> rightList;


	public Day1() {
		leftList = new ArrayList<>();
		rightList = new ArrayList<>();
		loadFileToLists();
	}


	public int solvePart1() {
		int totalDistance = 0;
		leftList.sort(Integer::compareTo);
		rightList.sort(Integer::compareTo);

		for (int i = 0; i < leftList.size(); i++) {
			int leftNum = leftList.get(i);
			int rightNum = rightList.get(i);
			totalDistance += leftNum > rightNum ?
			                 leftNum - rightNum :
			                       rightNum - leftNum;
		}
		return totalDistance;
	}

	public int solvePart2() {
		int similarityScore = 0;
		for (Integer leftValue : leftList) {
			for (Integer rightValue : rightList) {
				int occurrences = 0;
				if (Objects.equals(rightValue, leftValue)) {
					occurrences += 1;
				}
				similarityScore += leftValue * occurrences;
			}
		}
		return similarityScore;
	}

	private void loadFileToLists() {
		File inputFile = Utils.loadFile(1, 1);

		try (Scanner scanner = new Scanner(inputFile)) {
			while (scanner.hasNextLine()) {
				String[] scanline = scanner.nextLine().split("\\s+");
				leftList.add(Integer.parseInt(scanline[0]));
				rightList.add(Integer.parseInt(scanline[1]));
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
}
