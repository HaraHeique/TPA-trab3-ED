/* Main.java
* UVa 10920 -- Spiral Tap
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/
package uva10920;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {

    private static Scanner scanner;

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        while (true) {
            String buf = scanner.nextLine();
            ArrayList<Long> line = getLine(buf);
            long sz = line.get(0);
            long p = line.get(1);
            long x, y;
            if (sz == 0 && p == 0) {
                break;
            }

            /**
             * p = 1 is the center of matrix
             */
            if (p == 1) {
                x = Math.round((sz / 2)) + 1;
                output(x, x);
                continue;
            }

            long lower = 1;
            long upper = 1;
            int count = 0;

            /**
             * Find lower limit and upper limit
             */
            while (!(p >= lower && p <= upper)) {
                count++;
                lower = upper + 1;
                upper = upper + 4 * (2 * count + 1) - 4;
            }
            /**
             * x, y represents coordinates of upper bound
             */
            x = (sz + 1) / 2 + count;
            y = (sz + 1) / 2 + count;

            /**
             * flag and auxiliary to iterate the board in spiral
             */
            boolean isVertical = true;
            int i = -1;
            long actual = upper;
            int lowerLimit = (int) (sz + 1) / 2 - count;
            int upperLimit = (int) (sz + 1) / 2 + count;

            /**
             * find the piece "p" interating the board in spiral mode
             */
            while (actual != p) {

                if (isVertical) {
                    x += i;
                } else {
                    y += i;
                }

                if (isVertical) { // vertical moviment
                    if (i == -1 && x == lowerLimit) {
                        isVertical = false;
                    } else if (i == 1 && x == upperLimit) {
                        isVertical = false;
                        upperLimit--;
                    }
                } else { // horizontal moviment
                    if (i == -1 && y == lowerLimit) {
                        isVertical = true;
                        i = 1;
                        lowerLimit++;
                    } else if (i == 1 && y == upperLimit) {
                        isVertical = true;
                        i = -1;
                    }
                }
                actual--;
            }

            output(x, y);
        }
    }

    /**
     * Print output coordinates
     */
    public static void output(long x, long y) {
        System.out.printf("Line = %d, column = %d.\n", x, y);
    }

    /**
     * get all line mapped to int from string input
     *
     * @param line
     * @return
     */
    public static ArrayList<Long> getLine(String line) {
        ArrayList<String> res = new ArrayList<>(Arrays.asList(line.split(" ")));
        ArrayList<Long> l = new ArrayList<>(res.size());
        for (String element : res) {
            l.add(Long.parseLong(element));
        }
        return l;
    }
}
