/* Main.java
* UVa 11581 -- Grid successors
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/

import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        // total amount of grids
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            scanner.nextLine(); // blank line
            int[][] grid = new int[3][3];
            // fill the grid
            grid[0] = getLine(scanner.nextLine());
            grid[1] = getLine(scanner.nextLine());
            grid[2] = getLine(scanner.nextLine());
            // debugPrint(grid);

            int output = -1;
            while (!isZeroGrid(grid)) {
                grid = fn(grid);
                // debugPrint(grid);
                output++;
            }

            output(output);
        }
    }

    /**
     * function described in problem to apply in grid
     *
     * @param grid
     * @return
     */
    private static int[][] fn(int[][] grid) {
        int[][] h = new int[3][3];
        // line 0
        h[0][0] = (grid[0][1] + grid[1][0]) % 2;
        h[0][1] = (grid[0][0] + grid[1][1] + grid[0][2]) % 2;
        h[0][2] = (grid[0][1] + grid[1][2]) % 2;
        // line 1
        h[1][0] = (grid[0][0] + grid[1][1] + grid[2][0]) % 2;
        h[1][1] = (grid[0][1] + grid[1][0] + grid[1][2] + grid[2][1]) % 2;
        h[1][2] = (grid[1][1] + grid[0][2] + grid[2][2]) % 2;
        // line 2
        h[2][0] = (grid[2][1] + grid[1][0]) % 2;
        h[2][1] = (grid[2][0] + grid[1][1] + grid[2][2]) % 2;
        h[2][2] = (grid[2][1] + grid[1][2]) % 2;

        return h;
    }

    /**
     * print index output
     */
    private static void output(int index) {
        System.out.println(index);
    }

    /**
     * check if the current grid is only filled with 0
     *
     * @param grid
     * @return
     */
    private static boolean isZeroGrid(int[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * get all line mapped to int from string input
     *
     * @param line
     * @return
     */
    public static int[] getLine(String line) {
        char[] res = line.toCharArray();
        int[] mappedL = new int[3];
        for (int i = 0; i < 3; i++) {
            char[] data = { res[i] };
            mappedL[i] = Integer.parseInt(new String(data));
        }
        return mappedL;
    }

    public static void debugPrint(int[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.println("");
    }
}
