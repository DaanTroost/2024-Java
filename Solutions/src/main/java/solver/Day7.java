package solver;

import java.util.*;

public class Day7 extends BaseSolver {

	public static long canMatch(long lhs, long total, long[] rhs, int ri, int ops) {
		if (lhs == total && rhs.length == ri) {
			return total;
		} else if (rhs.length == ri) {
			return 0;
		} else if (total > lhs) {
			return 0;
		} else {
			long result = canMatch(lhs, total + rhs[ri], rhs, ri+1, ops);
			if (result == lhs) {
				return lhs;
			}
			result = canMatch(lhs, total * rhs[ri], rhs, ri+1, ops);
			if (result == lhs) {
				return lhs;
			}
			if (ops == 3) {
				return canMatch(lhs, Long.parseLong(total + "" + rhs[ri]), rhs, ri + 1, ops);
			} else {
				return 0;
			}
		}
	}

	@Override
	protected String solvePart1(List<String> input) {
		long partOne = 0;
		for (String line : input) {
			String[] S = line.split(": ");
			long lhs = Long.parseLong(S[0]);
			long[] rhs = Arrays.stream(S[1].split(" ")).mapToLong(Long::parseLong).toArray();
			partOne += canMatch(lhs, rhs[0], rhs, 1, 2);

		}
		return String.valueOf(partOne);
	}

	@Override
	protected String solvePart2(List<String> input) {
		long partTwo = 0;
		for (String line : input) {
			String[] S = line.split(": ");
			long lhs = Long.parseLong(S[0]);
			long[] rhs = Arrays.stream(S[1].split(" ")).mapToLong(Long::parseLong).toArray();
			partTwo += canMatch(lhs, rhs[0], rhs, 1, 3);
		}
		return String.valueOf(partTwo);
	}

	protected Day7() {
		super(7);
	}

	public static void main(String[] args) {
		new Day7();
	}
}


//import org.junit.jupiter.api.*;
//
//import java.util.*;
//import java.util.Map.*;
//import java.util.stream.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class Day7 extends BaseSolver
//{
//	private void getOperatorCombos(int index, int length, String current, List<String> combos)
//	{
//		if (index == length)
//		{
//			combos.add(current);
//		} else {
//			current += "+";
//			getOperatorCombos(index + 1, length, current, combos);
//			current = current.substring(0, current.length() - 1);
//			current += "*";
//			getOperatorCombos(index + 1, length, current, combos);
//		}
//	}
//
//	private boolean canMatch(long target, List<Long> numbers, char[] operators)
//	{
//		long total = numbers.getFirst();
//		int j = 0;
//		for (int i = 1; i < numbers.size(); i++)
//		{
//			long val = numbers.get(i);
//			char operator = operators[j++];
//			total = operator == '+' ? total + val : total * val;
//			if (total > target)
//			{
//				return false;
//			}
//		}
//		return total == target;
//	}
//
//	@Override
//	protected String solvePart1(List<String> input)
//	{
//		long calibrationTotal = 0L;
//		for (String line: input)
//		{
//			String[] lineArr = line.split(": ");
//			long target = Long.parseLong(lineArr[0]);
//			List<Long> operands =
//				Arrays.stream(lineArr[1].split(" ")).map(Long::valueOf).toList();
//			List<String> operators = new ArrayList<>();
//			getOperatorCombos(0, operands.size() - 1, "", operators);
//			for (String opCombo : operators) {
//				if (canMatch(target, operands, opCombo.toCharArray())) {
//					calibrationTotal += target;
//				}
//			}
//		}
//		return String.valueOf(calibrationTotal);
//	}
//
//	@Override
//	protected String solvePart2(List<String> input)
//	{
//		return "";
//	}
//
//	protected Day7()
//	{
//		super(7);
//	}
//
//	public static void main(String[] args)
//	{
//		new Day7();
//	}
//
//	@Test
//	public void shouldMatch()
//	{
//		long target = 45;
//		List<Long> nums = new ArrayList<>(List.of(4L, 1L, 3L, 3L));
//		List<String> operators = new ArrayList<>();
//		getOperatorCombos(0, nums.size() - 1, "", operators);
//		boolean matched = false;
//		for (String o : operators)
//		{
//			matched = canMatch(target, nums, o.toCharArray());
//			if (matched)
//			{
//				break;
//			}
//		}
//		assertTrue(matched);
//	}
//
//	@Test
//	public void shouldFail()
//	{
//		long target = 38;
//		List<Long> nums = new ArrayList<>(List.of(4L, 1L, 3L, 3L));
//		List<String> operators = new ArrayList<>();
//		getOperatorCombos(0, nums.size() - 1, "", operators);
//		boolean matched = false;
//		for (String o : operators)
//		{
//			matched = canMatch(target, nums, o.toCharArray());
//			if (matched)
//			{
//				break;
//			}
//		}
//		assertFalse(matched);
//	}
//
//	@Test
//	public void exampleInput() {
//		Map<Long, List<Long>> inputs = new HashMap<>();
//		inputs.put(190L, List.of(10L, 19L));
//		inputs.put(3267L, List.of(81L, 40L, 27L));
//		inputs.put(83L, List.of(83L, 5L));
//		inputs.put(156L, List.of(15L, 6L));
//		inputs.put(7290L, List.of(6L, 8L, 6L, 15L));
//		inputs.put(161011L, List.of(16L, 10L, 13L));
//		inputs.put(192L, List.of(17L, 8L, 14L));
//		inputs.put(21037L, List.of(9L, 7L, 18L, 13L));
//		inputs.put(292L, List.of(11L, 6L, 16L, 20L));
//
//		Map<Long, Boolean> matches = new HashMap<>();
//		for (Long key : inputs.keySet()) {
//			matches.put(key, false);
//		}
//
//		Map<Long, Boolean> expectedMatches = new HashMap<>();
//		expectedMatches.put(190L, true);
//		expectedMatches.put(292L, true);
//		expectedMatches.put(3267L, true);
//		expectedMatches.put(83L, false);
//		expectedMatches.put(156L, false);
//		expectedMatches.put(7290L, false);
//		expectedMatches.put(161011L, false);
//		expectedMatches.put(192L, false);
//		expectedMatches.put(21037L, false);
//
//		for (Entry<Long, List<Long>> entry : inputs.entrySet())
//		{
//			List<String> operators = new ArrayList<>();
//			getOperatorCombos(0, entry.getValue().size() - 1, "", operators);
//			for (String opCombo : operators)
//			{
//				if (canMatch(entry.getKey(), entry.getValue(), opCombo.toCharArray()))
//				{
//					matches.put(entry.getKey(), true);
//				}
//			}
//		}
//
//		for (Long key : matches.keySet()) {
//			assertEquals(matches.get(key), expectedMatches.get(key));
//		}
//
//	}
//}
