/* Main.java
* UVa 10172 -- The Lonesome Cargo Distribuitor
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Main {

    private static Scanner scanner;
    private static int n;

    public static void main(String[] args) {
        /**
         * to start index with 1 I'm using HashMap
         */
        HashMap<Integer, Queue<Integer>> b = new HashMap<Integer, Queue<Integer>>();
        Queue<Integer> currentQueue;

        scanner = new Scanner(System.in);

        // buffer output to print one time
        StringBuilder output = new StringBuilder();

        // total cases of set
        int totalSet = scanner.nextInt();

        // run program with "totalSet" number of output(s)
        for (int x = 0; x < totalSet; x++) {
            n = scanner.nextInt(); // number of stations in the ring
            int s = scanner.nextInt(); // capacity of your cargo carrier
            int q = scanner.nextInt(); // maximum number of cargoes the queue in platform "b" can accommodate

            /**
             * Create the queues in map and put input
             */
            for (int i = 1; i <= n; i++) {
                int qi = scanner.nextInt();
                currentQueue = new LinkedList<>();
                b.put(i, currentQueue);
                for (int j = 0; j < qi; j++) {
                    currentQueue.add(scanner.nextInt());
                }
            }

            int minutes = 0;
            Queue<Integer> platform;
            Stack<Integer> carrier = new Stack<Integer>();
            int cargo;
            int i = 1;
            while (!isEmpty(b) || !carrier.empty()) {
                minutes += 2;
                platform = b.get(i);

                /**
                 * If carrier is not empty and top can fit to "a" or "b" then unload
                 */
                while (!carrier.empty() && (carrier.peek() == i || platform.size() < q)) {
                    cargo = carrier.pop();
                    if (cargo != i) {
                        platform.add(cargo);
                    }
                    minutes++;
                }

                /**
                 * Load carrier if "b" has cargos and carrier has free space
                 */
                while (platform.size() > 0 && carrier.size() < s) {
                    carrier.push(platform.poll());
                    minutes++;
                }

                // circular motion
                i = i % n + 1;
            }
            output.append(Integer.toString(minutes == 0 ? minutes : minutes - 2));
            output.append("\n");
        }

        System.out.print(output);

    }

    /**
     * return true if platform b is empty
     *
     * @param b
     * @return
     */
    public static boolean isEmpty(HashMap<Integer, Queue<Integer>> b) {
        for (int i = 1; i <= n; i++) {
            if (b.get(i).size() > 0) {
                return false;
            }
        }
        return true;
    }
}
