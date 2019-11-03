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
            if(line.get(0) == 1){
                output(true);
                continue;
            }
            
            boolean[] arrayBool = new boolean[line.get(0) - 1];
            /**
             * Calculate the difference between sequence and store in arrayBoll
             */
            for (int i = 1; i < line.get(0); i++) {
                int result = Math.abs(line.get(i) - line.get(i + 1));
                if(result > 0 && result < line.get(0)){
                    arrayBool[result - 1] = true;
                }
            }
            boolean isJolly = true;
            /**
             * Checks if the ArrayList "results" has a sequence from 1 to (n-1)
             */
            for (boolean exist : arrayBool) {
                isJolly = isJolly && exist;
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
     * get all line mapped to int from string input
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
