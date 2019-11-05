/* Main.java
* UVa 01062 -- Containers
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/
package uva01062;

import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Main {

    private static Scanner scanner;

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int caseCount = 1;

        while (!isLineEnd(line)) {

            /**
             * Vector of stacks (java vector get is O(1))
             */
            Vector<Stack<Character>> stacks = new Vector<>();

            /**
             * for each characters in line find the first stack that can be stacked
             */
            for (int i = 0; i < line.length(); i++) {
                char container = line.charAt(i);
                boolean isStacked = false;

                int j = 0;
                while (j < stacks.size() && !isStacked) {
                    Stack<Character> currentStack = stacks.get(j);
                    if (currentStack.peek() >= container) { // the ships is alphabetic order
                        currentStack.push(container);
                        isStacked = true;
                    }
                    j++;
                }

                if (!isStacked) { // couldn't find when create a new stack
                    stacks.add(createStackWithElement(container));
                }
            }

            // output message
            System.out.printf("Case %d: %d\n", caseCount, stacks.size());

            line = scanner.nextLine();
            caseCount++;
        }

    }

    /**
     * create a character stack and push the first element
     *
     * @param element
     * @return
     */
    public static Stack<Character> createStackWithElement(char element) {
        Stack<Character> stack = new Stack<>();
        stack.push(element);
        return stack;
    }

    /**
     * verify if line is end token
     *
     * @param line
     * @return
     */
    public static boolean isLineEnd(String line) {
        return line.compareTo("end") == 0;
    }
}
