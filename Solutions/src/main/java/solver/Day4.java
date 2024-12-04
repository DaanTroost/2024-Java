package solver;

import common.*;

import java.io.*;
import java.util.*;

public class Day4 {
	private char[][] getWordSearchField(List<String> input) {
		char[][] wordSearchField = new char[input.getFirst().length()][input.size()];
		for (int y = 0; y < input.size(); y++) {
			String line = input.get(y);
			for (int x = 0; x < line.length(); x++) {
				wordSearchField[y][x] = line.charAt(x);
			}
		}
		return wordSearchField;
	}

	private boolean findWest(int x, int y, char[][] wordSearchField) {
		return x >= 3 && wordSearchField[y][x - 1] == 'M' && wordSearchField[y][x - 2] == 'A'
			       && wordSearchField[y][x - 3] == 'S';
	}

	private boolean findNorthwest( int x,  int y,  char[][] wordSearchField) {
		return x >= 3 && y >= 3 && wordSearchField[y - 1][x - 1] == 'M' && wordSearchField[y - 2][x - 2] == 'A'
			       && wordSearchField[y - 3][x - 3] == 'S';
	}

	private boolean findNorth( int x,  int y,  char[][] wordSearchField) {
		return y >= 3 && wordSearchField[y - 1][x] == 'M' && wordSearchField[y - 2][x] == 'A'
			       && wordSearchField[y - 3][x] == 'S';
	}

	private boolean findNortheast( int x,  int y,  char[][] wordSearchField) {
		return x < wordSearchField[0].length - 3 && y >= 3 && wordSearchField[y - 1][x + 1] == 'M'
			       && wordSearchField[y - 2][x + 2] == 'A' && wordSearchField[y - 3][x + 3] == 'S';
	}

	private boolean findEast( int x,  int y,  char[][] wordSearchField) {
		return x < wordSearchField[0].length - 3 && wordSearchField[y][x + 1] == 'M' && wordSearchField[y][x + 2] == 'A'
			       && wordSearchField[y][x + 3] == 'S';
	}

	private boolean findSoutheast( int x,  int y,  char[][] wordSearchField) {
		return x < wordSearchField[0].length - 3 && y < wordSearchField.length - 3 && wordSearchField[y + 1][x + 1] == 'M'
			       && wordSearchField[y + 2][x + 2] == 'A' && wordSearchField[y + 3][x + 3] == 'S';
	}

	private boolean findSouth( int x,  int y,  char[][] wordSearchField) {
		return y < wordSearchField.length - 3 && wordSearchField[y + 1][x] == 'M' && wordSearchField[y + 2][x] == 'A'
			       && wordSearchField[y + 3][x] == 'S';
	}

	private boolean findSouthwest( int x,  int y,  char[][] wordSearchField) {
		return x >= 3 && y < wordSearchField.length - 3 && wordSearchField[y + 1][x - 1] == 'M'
			       && wordSearchField[y + 2][x - 2] == 'A' && wordSearchField[y + 3][x - 3] == 'S';
	}

	private int findMasCrossShape(final char[][] wordSearch) {
		int masCrossShapes = 0;
		for (int y = 1; y < wordSearch.length - 1; y++) {
			for (int x = 1; x < wordSearch[y].length - 1; x++) {
				if (wordSearch[y][x] == 'A') {
					if (wordSearch[y - 1][x - 1] == 'M' && wordSearch[y + 1][x + 1] == 'S' && wordSearch[y + 1][x - 1] == 'M' && wordSearch[y - 1][x + 1] == 'S') {
						masCrossShapes++;
					} else if (wordSearch[y - 1][x - 1] == 'S' && wordSearch[y + 1][x + 1] == 'M' && wordSearch[y + 1][x - 1] == 'S' && wordSearch[y - 1][x + 1] == 'M') {
						masCrossShapes++;
					} else if (wordSearch[y - 1][x - 1] == 'M' && wordSearch[y + 1][x + 1] == 'S' && wordSearch[y + 1][x - 1] == 'S' && wordSearch[y - 1][x + 1] == 'M') {
						masCrossShapes++;
					} else if (wordSearch[y - 1][x - 1] == 'S' && wordSearch[y + 1][x + 1] == 'M' && wordSearch[y + 1][x - 1] == 'M' && wordSearch[y - 1][x + 1] == 'S') {
						masCrossShapes++;
					}
				}
			}
		}
		return masCrossShapes;
	}

	public int solvePart1(){
		int numMatches = 0;
		List<String> rawSearchField = loadFileAsListString();
		char[][] wordSearchField = getWordSearchField(rawSearchField);

		for (int y = 0; y < wordSearchField.length; y++) {
			for (int x = 0; x < wordSearchField[y].length; x++) {
				if (wordSearchField[y][x] == 'X') {
					if (findWest(x, y, wordSearchField))
						numMatches++;
					if (findNorthwest(x, y, wordSearchField))
						numMatches++;
					if (findNorth(x, y, wordSearchField))
						numMatches++;
					if (findNortheast(x, y, wordSearchField))
						numMatches++;
					if (findEast(x, y, wordSearchField))
						numMatches++;
					if (findSoutheast(x, y, wordSearchField))
						numMatches++;
					if (findSouth(x, y, wordSearchField))
						numMatches++;
					if (findSouthwest(x, y, wordSearchField))
						numMatches++;
				}
			}

		}
		return numMatches;
	}

	public int solvePart2() {
		int numMatches = 0;
		List<String> rawSearchField = loadFileAsListString();
		char[][] wordSearchField = getWordSearchField(rawSearchField);
		return findMasCrossShape(wordSearchField);
	}

	private static List<String> loadFileAsListString() {
		File input = Utils.loadFile(4);
		List<String> rawSearchField = new ArrayList<>();
		try (Scanner scanner = new Scanner(input)) {
			while (scanner.hasNextLine()) {
				rawSearchField.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.err.println("Can't find file.");
		}
		return rawSearchField;
	}
}
