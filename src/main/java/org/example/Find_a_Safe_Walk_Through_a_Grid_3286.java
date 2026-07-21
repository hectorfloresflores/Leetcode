package org.example;

import java.util.*;

public class Find_a_Safe_Walk_Through_a_Grid_3286 {

	class Pair {
		int col;
		int row;

		public Pair(int row, int col) {
			this.col = col;
			this.row = row;
		}
	}

	static int[][] healthMatrix;
	public boolean findSafeWalk(List<List<Integer>> grid, int health) {
		healthMatrix = new int[grid.getFirst().size()][grid.size()];

				return recursiveFindSafeWalk(grid, health, 0, 0);
		//return dynamicProgrammingFindSafeWalk(grid, health);
    	
	}


	boolean recursiveFindSafeWalk(List<List<Integer>> grid, int health, int x, int y) {

		if (x >= 0 && x < grid.getFirst().size() && y >= 0 && y < grid.size() && healthMatrix[y][x] != -1) {
			if (health == 0) {
				healthMatrix[y][x] = -1;
				return false;
			}
			int value = grid.get(y).get(x);
			if (value > 0) {
				health--;
			}


			healthMatrix[y][x] = Math.max(health, healthMatrix[y][x]);

			if (x == grid.getFirst().size() - 1 && y == grid.size() - 1) {
				if (health > 0) {
					return true;
				} else {
					return false;
				}
			
			}



			return recursiveFindSafeWalk(grid, health, x+1, y) ||
				recursiveFindSafeWalk(grid, health, x, y+1) ||
				recursiveFindSafeWalk(grid, health, x-1, y) ||
				recursiveFindSafeWalk(grid, health, x, y-1);
		}

		return false;

	}

	boolean bfsFindSafeWalk(List<List<Integer>> grid, int health) {

		int rowSize = grid.size();
		int colSize = grid.getFirst().size();

		int[][] minCost = new int[rowSize][colSize];

		for (int[] rows : minCost) {
			Arrays.fill(rows, Integer.MAX_VALUE);
		}

		minCost[0][0] = grid.getFirst().getFirst();

		Deque<Pair> deque = new ArrayDeque<>();
		deque.offer(new Pair(0, 0));

		// right, up, left
		final int[] directions = new int[]{0, -1, 0, 1, 0};
		while (!deque.isEmpty()) {

			Pair pair = deque.poll();

			for (int i = 0; i < 4; i++) {

				int nextRow = pair.row + directions[i + 1];
				int nextCol = pair.col + directions[i];

				if (nextCol >= 0 && nextCol < colSize &&
					nextRow >= 0 && nextRow < rowSize &&
						// If current health plus min next is less than next min means we should remove max value and start incrementing
						grid.get(nextRow).get(nextRow) + minCost[pair.row][pair.col] < minCost[nextRow][nextCol]) {

					minCost[nextRow][nextCol] = minCost[pair.row][pair.col] + grid.get(nextRow).get(nextRow);

					deque.offer(new Pair(nextRow, nextCol));

				}

			}

		}

		return minCost[rowSize - 1][colSize - 1] < health ? true : false;
	}

	public static void main(String[] args) {
//		List<List<Integer>> grid = List.of(
//				Arrays.asList(0,1,0,0,0),
//				Arrays.asList(0,1,0,1,0),
//				Arrays.asList(0,0,0,1,0)
//		);
//		List<List<Integer>> grid = List.of(
//				Arrays.asList(0,0),
//				Arrays.asList(1,1)
//		);
		List<List<Integer>> grid = List.of(
				Arrays.asList(1,1,1),
				Arrays.asList(1,0,1),
				Arrays.asList(1,1,1)
		);
//		List<List<Integer>> grid = List.of(
//				Arrays.asList(1,1,1,1)
//		);
		Find_a_Safe_Walk_Through_a_Grid_3286 s = new Find_a_Safe_Walk_Through_a_Grid_3286();
		System.out.println(s.bfsFindSafeWalk(grid, 5));
	}

}
