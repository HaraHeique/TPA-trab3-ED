/* Main.java
* UVa 11849 -- CD
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/

import java.util.Scanner;
import java.util.HashSet;

public class Main {

    private static Scanner scanner;

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        StringBuilder output = new StringBuilder();
        while (true) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            if (n == 0 && m == 0) {
                break;
            }

            HashSet<Integer> jack = new HashSet<>();
            int numberOfCds = 0;

            /**
             * Save all Jack CDs in HashSet
             */
            for (int i = 0; i < n; i++) {
                jack.add(scanner.nextInt());
            }

            /**
             * For each Jill CD count the intersection with Jack CDs
             */
            for (int i = 0; i < m; i++) {
                if (jack.contains(scanner.nextInt())) { // Jill cd intersec Jack
                    numberOfCds++;
                }
            }
            output.append(numberOfCds).append("\n");
        }
        System.out.print(output);
    }
}
