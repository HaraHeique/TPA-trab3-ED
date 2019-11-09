/* Main.java
* UVa 00514 -- Rails
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/
package uva00514;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static Scanner scanner;

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        /**
         * End program with n = 0
         */
        while (n > 0) {

            /**
             * flag to break loop with coach is 0
             */
            boolean isEnd = false;
            while (!isEnd) {

                String[] coaches = scanner.nextLine().split(" ");
                Stack<Integer> station = new Stack<>();

                int count = 0;
                /**
                 * for each coach of train
                 */
                for (int i = 0; i < n; i++) {
                    int coach = Integer.parseInt(coaches[i]);
                    // System.out.printf("coach = %d\n", coach);
                    if (coach == 0) { // if coach = 0 is end of block
                        isEnd = true;
                        break;
                    }

                    while (count < n && coach != count) {
                        if (station.size() > 0 && coach == station.peek())
                            break;
                        station.push(++count);
                    }

                    if (station.peek() == coach)
                        station.pop();

                }

                if (!isEnd) {
                    output(station);
                }

            }

            n = Integer.parseInt(scanner.nextLine());
            System.out.printf("\n"); // fix the presentation error
        }
    }

    /**
     * output print
     *
     * @param s
     */
    private static void output(Stack<Integer> s) {
        System.out.println(s.size() == 0 ? "Yes" : "No");
    }
}
