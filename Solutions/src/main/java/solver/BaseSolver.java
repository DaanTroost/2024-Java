package solver;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

public abstract class BaseSolver {
	protected List<String> reportArray = new ArrayList<>();

	protected BaseSolver(int day) {
		reportArray = acquireInput(day);
		solve();
	}

	protected void solve(){
		Instant start = Instant.now();
		String answerPart1 = solvePart1(reportArray);
		Instant end = Instant.now();
		System.out.println("The answer to part 1 is: " + answerPart1);
		System.out.println("The time needed to arrive at this answer is: "
			                   + Duration.between(start, end).toMillis() + " ms.");

		start = Instant.now();
		String answerPart2 = solvePart2(reportArray);
	    end = Instant.now();
		System.out.println("The answer to part 2 is: " + answerPart2);
		System.out.println("The time needed to arrive at this answer is: "
			                   + Duration.between(start, end).toMillis() + " ms.");
	}


	private List<String> acquireInput(int day) {
		String filepathString = String.format("src/main/resources/input/day%d/input.txt", day);

		try (Stream<String> fileStream =
			     Files.lines(Path.of(filepathString))) {
			return fileStream.toList();
		} catch (IOException e) {
			System.err.printf("File not found at path %s", filepathString);
		}
		return new ArrayList<>();
	}

	protected abstract String solvePart1(List<String> input);
	protected abstract String solvePart2(List<String> input);
}
