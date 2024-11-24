package common;

import java.io.*;
import java.util.*;

public class Utils {
	public static File loadFile(int day, int part) {
		return new File(String.format(
			"src/main/resources/AoC-2024-Input/day%d/part%d.txt",
			day,
			part)
		);
	}

	public static String loadFileAsString(int day, int part) {
		File inputFile = loadFile(day, part);
		StringBuilder input = new StringBuilder();
		try (Scanner s = new Scanner(inputFile)) {
			while (s.hasNextLine()) {
				input.append(s.nextLine());
				input.append("\n");
			}
		} catch (IOException e) {
			System.err.println("Scanner could not find file.");
		}
		return input.toString();
	}
}
