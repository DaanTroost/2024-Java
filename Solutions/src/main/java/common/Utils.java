package common;

import java.io.*;
import java.util.*;

public class Utils {
	public static File loadFile(int day) {
		return new File(String.format(
			"src/main/resources/input/day%d/input.txt",
			day
			)
		);
	}

	public static String loadFileAsString(int day) {
		File inputFile = loadFile(day);
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
