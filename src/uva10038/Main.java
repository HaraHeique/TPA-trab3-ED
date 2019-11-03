/* Main.java
* UVa 10038 -- Jolly Jumpers
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/
package uva10038;

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
        while (scanner.hasNextLine()) {
            String buf = scanner.nextLine();
            ArrayList<Integer> line = getLine(buf);
            /**
             * It is understood through the "debug" site that the base case is a sequence
             * with size 1, being Jolly
             */
            if (line.size() == 1) {
                output(true);
                continue;
            }
            ArrayList<Integer> results = new ArrayList<>();
            /**
             * Calculate the difference between sequence and store in ArrayList
             */
            for (int i = 0; i < line.size() - 1; i++) {
                int result = Math.abs(line.get(i) - line.get(i + 1));
                results.add(result);
            }
            boolean isJolly = true;
            /**
             * Checks if the ArrayList "results" has a sequence from 1 to (n-1)
             */
            for (int i = 1; i < results.size(); i++) {
                isJolly = isJolly && (results.get(i - 1) == i);
                if (!isJolly)
                    break;
            }
            // System.out.println(line);
            output(isJolly);
        }
    }

    private static void output(boolean isJolly) {
        if (isJolly)
            System.out.println("Jolly");
        else
            System.out.println("Not jolly");
    }

    /**
     * get all line maped to int from string input
     *
     * @param line
     * @return
     */
    public static ArrayList<Integer> getLine(String line) {
        ArrayList<String> res = new ArrayList<>(Arrays.asList(line.split(" ")));
        ArrayList<Integer> l = new ArrayList<>(res.size());
        for (String element : res) {
            l.add(Integer.parseInt(element));
        }
        return l;
    }
}
