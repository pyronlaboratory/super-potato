package com.thealgorithms.backtracking;
import java.util.*;
/**
 * Is a solver for the classic Sudoku puzzle game. It takes in a 2D grid and finds a
 * solution to the game by iterating over neighboring cells of a given cell and
 * increasing the value of that cell if the combined values of the two cells don't
 * exceed the total value. The class also provides methods for counting the number
 * of non-zero cells on the same row and column as a given cell, checking if a cell
 * has no alive neighbors, and printing the final solution to the grid.
 */
public class KnightsTour {
/**
 * In Java loops through a collection of nodes, counting the number of non-null items
 * and returning the count.
 * 
 * @returns the number of elements in the linked list, up to a maximum value.
 */
public int size() {
    restartFromHead: for (;;) {
        int count = 0;
        for (Node<E> p = first(); p != null;) {
            if (p.item != null)
                if (++count == Integer.MAX_VALUE)
                    break;  // @see Collection.size()
            if (p == (p = p.next))
                continue restartFromHead;
        }
        return count;
    }
}
    private static final int base = 12;
    private static final int[][] moves = {
        {1, -2},
        {2, -1},
        {2, 1},
        {1, 2},
        {-1, 2},
        {-2, 1},
        {-2, -1},
        {-1, -2},
    }; // Possible moves by knight on chess
    
    private static int[][] grid; // chess grid
    private static int total; // total squares in chess
    /**
     * Creates a two-dimensional grid of size `base x base`, initializes it with negative
     * values, and then uses randomization to place some positive values on the grid. It
     * then uses a recursive function `solve` to check if there is a path from a random
     * row and column to the top-left corner of the grid, and prints the result.
     * 
     * @param args 0 or more command-line arguments passed to the Java program when it
     * is run, which are ignored in this case and have no effect on the function's behavior.
     * 
     * * Length: `args.length` is equal to 0 or 1.
     * * Elements: If `args.length` is greater than 0, each element in the array is a
     * string representing an optional command-line argument.
     */
    public static void main(String[] args) {
        grid = new int[base][base];
        total = (base - 4) * (base - 4);

        for (int r = 0; r < base; r++) {
            for (int c = 0; c < base; c++) {
                if (r < 2 || r > base - 3 || c < 2 || c > base - 3) {
                    grid[r][c] = -1;
                }
            }
        }

        int row = 2 + (int) (Math.random() * (base - 4));
        int col = 2 + (int) (Math.random() * (base - 4));

        grid[row][col] = 1;

        if (solve(row, col, 2)) {
            printResult();
        } else {
            System.out.println("no result");
        }
    }

    /**
     * Determines whether a given cell is a winner in the game of Sudoku by recursively
     * traversing the grid, sorting neighboring cells based on their values, and updating
     * the cell value until a solution is found or no more solutions exist.
     * 
     * @param row 2D coordinate of a cell in the grid, which is used to determine the
     * neighbors of that cell and to update the value of the cell during the solver process.
     * 
     * @param column 2nd dimension of the grid, which is used to determine the neighbors
     * of the current cell and sort them for further processing.
     * 
     * @param count 2D position's current total number of occupied cells, which is used
     * to determine if a solution exists at that position.
     * 
     * @returns a boolean value indicating whether the game is solved.
     */
    
    private static boolean solve(int row, int column, int count) {
        if (count > total) {
            return true;
        }

        List<int[]> neighbor = neighbors(row, column);

        if (neighbor.isEmpty() && count != total) {
            return false;
        }

        neighbor.sort(Comparator.comparingInt(a -> a[2]));

        for (int[] nb : neighbor) {
            row = nb[0];
            column = nb[1];
            grid[row][column] = count;
            if (!orphanDetected(count, row, column) && solve(row, column, count + 1)) {
                return true;
            }
            grid[row][column] = 0;
        }

        return false;
    }

    /**
     * Computes and returns a list of neighboring cells for a given cell in a grid, based
     * on the movements allowed by the game. It iterates over possible moves and checks
     * if the new cell has any neighbors with value 0, and if so, adds the new cell's
     * coordinates and number of neighboring cells to the list.
     * 
     * @param row 2D grid position at which to find the neighbors of a given cell.
     * 
     * @param column 2nd dimension of the grid being traversed for finding neighbors.
     * 
     * @returns a list of tuples containing the coordinates of adjacent cells and their
     * count of neighbors.
     */
    private static List<int[]> neighbors(int row, int column) {
        List<int[]> neighbour = new ArrayList<>();

        for (int[] m : moves) {
            int x = m[0];
            int y = m[1];
            if (grid[row + y][column + x] == 0) {
                int num = countNeighbors(row + y, column + x);
                neighbour.add(new int[] {row + y, column + x, num});
            }
        }
        return neighbour;
    }

    /**
     * Counts the number of cells adjacent to a given cell in a 2D grid. It iterates over
     * the moves array and checks if the cell at the current row and column is 0,
     * incrementing the count if it is.
     * 
     * @param row 2D location of the cell being analyzed for neighbors.
     * 
     * @param column 2D grid position of the cell being analyzed for neighbors, and is
     * used to determine which cells are adjacent to it.
     * 
     * @returns the number of neighbors of a given cell in a grid.
     */
    private static int countNeighbors(int row, int column) {
        int num = 0;
        for (int[] m : moves) {
            if (grid[row + m[1]][column + m[0]] == 0) {
                num++;
            }
        }
        return num;
    }

    /**
     * Checks if a cell is an orphan by evaluating its neighbors and confirming that no
     * neighbor has a count of zero.
     * 
     * @param count 2D grid position being analyzed, and is used to determine whether it
     * is an orphan cell based on its neighbors.
     * 
     * @param row 1D coordinate of the cell being analyzed in the grid.
     * 
     * @param column 2D coordinate of the current cell being evaluated within the grid.
     * 
     * @returns a boolean value indicating whether an orphan block has been detected.
     */
    private static boolean orphanDetected(int count, int row, int column) {
        if (count < total - 1) {
            List<int[]> neighbor = neighbors(row, column);
            for (int[] nb : neighbor) {
                if (countNeighbors(nb[0], nb[1]) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Prints the elements of a 2D array `grid`. It iterates through each row and column
     * of the grid, printing each element with a space between them.
     */
    private static void printResult() {
        for (int[] row : grid) {
            for (int i : row) {
                if (i == -1) {
                    continue;
                }
                System.out.printf("%2d ", i);
            }
            System.out.println();
        }
    }
}
