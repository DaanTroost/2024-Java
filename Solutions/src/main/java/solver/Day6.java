package solver;

import java.util.List;

public class Day6 extends BaseSolver {

	private static final int UP = 0;
	private static final int RIGHT = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;

	protected Day6() {
		super(6);
	}

	private char[][] getGrid( List<String> input) {
		char[][] grid = new char[input.size()][input.getFirst().length()];
		for (int y = 0; y < input.size(); y++) {
			String line = input.get(y);
			for (int x = 0; x < line.length(); x++) {
				grid[y][x] = line.charAt(x);
			}
		}
		return grid;
	}

	private boolean hasLeftArea( int x,  int y,  char[][] grid) {
		return x < 0 || x >= grid[0].length || y < 0 || y >= grid.length;
	}

	private Position getGuardPosition( char[][] grid) {
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				if (grid[y][x] == '^') {
					return new Position(x, y);
				}
			}
		}
		return null;
	}

	private int getNextY( int y,  int dir) {
		return switch (dir) {
			case LEFT, RIGHT -> y;
			case UP -> y - 1;
			default -> y + 1;
		};
	}

	private int getNextX( int x,  int dir) {
		return switch (dir) {
			case UP, DOWN -> x;
			case LEFT -> x - 1;
			default -> x + 1;
		};
	}

	private boolean isNextPosObstruction( int x,  int y,  char[][] grid) {
		return grid[y][x] == '#';
	}

	private void markPath( int x,  int y, int dir,  char[][] grid) {
		grid[y][x] = 'X';
		int nextX = getNextX(x, dir);
		int nextY = getNextY(y, dir);
		if (hasLeftArea(nextX, nextY, grid)) {
			return;
		}
		while (isNextPosObstruction(nextX, nextY, grid)) {
			dir = (dir + 1) % 4;
			nextX = getNextX(x, dir);
			nextY = getNextY(y, dir);
		}
		markPath(nextX, nextY, dir, grid);
	}

	private int countDistinctPositions( char[][] grid) {
		int total = 0;
		for (char[] chars : grid) {
			for (char aChar : chars) {
				if (aChar == 'X')
					total++;
			}
		}
		return total;
	}

	private boolean hasCycle( int x,  int y, int d,  char[][] grid,  boolean[][][] path) {
		if (path[x][y][d]) {
			return true;
		} else {
			path[x][y][d] = true;
			int nextX = getNextX(x, d);
			int nextY = getNextY(y, d);
			if (hasLeftArea(nextX, nextY, grid)) {
				return false;
			}
			while (isNextPosObstruction(nextX, nextY, grid)) {
				d = (d + 1) % 4;
				nextX = getNextX(x, d);
				nextY = getNextY(y, d);
			}
			return hasCycle(nextX, nextY, d, grid, path);
		}
	}

	private int countCycleObstructions( char[][] grid,  char[][] path,  int startX,  int startY) {
		int total = 0;
		for (int y = 0; y < path.length; y++) {
			for (int x = 0; x < path[y].length; x++) {
				if (path[y][x] == 'X') {
					grid[y][x] = '#';
					if (hasCycle(startX, startY, 0, grid, new boolean[grid[0].length][grid.length][4])) {
						total++;
					}
					grid[y][x] = '.';
				}
			}
		}

		return total;
	}
	
	public static void main( String... args) {
		new Day6();
	}

	@Override
	protected String solvePart1(List<String> input) {
		char[][] grid = getGrid(input);
		Position guard = getGuardPosition(grid);
		markPath(guard.x(), guard.y(), 0, grid);
		return String.valueOf(countDistinctPositions(grid));
	}

	@Override
	protected String solvePart2(List<String> input) {
		char[][] grid = getGrid(input);
		char[][] pathGrid = getGrid(input);
		Position guard = getGuardPosition(pathGrid);
		markPath(guard.x(), guard.y(), 0, pathGrid);
		return String.valueOf(countCycleObstructions(grid, pathGrid, guard.x(), guard.y()));
	}

	record Position(int x, int y) {}
}