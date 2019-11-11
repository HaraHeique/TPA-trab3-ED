/* Main.java
* UVa 11988 -- Broken Keyboard
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/

import java.util.Scanner;
import java.util.LinkedList;

public class Main {

    private static Scanner scanner;

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            LinkedList<Character> lst = new LinkedList<>();
            // start by adding at the end of the list
            boolean isFirst = false;
            int indexFirst = 0;
            // traverse the entire input sequence of the current line
            for (int i = 0; i < line.length(); i++) {
                char current = line.charAt(i);
                if (current == '[') { // if '[' is home key, then add on first the next characters
                    isFirst = true;
                    indexFirst = 0;
                } else if (current == ']') // if ']' is end key, then add on last the next characters
                    isFirst = false;
                else {
                    if (isFirst) {
                        /**
                         * When the home key is pressed the first character is inserted and the
                         * following characters right after it and before all already entered.
                         */
                        lst.add(indexFirst, current);
                        indexFirst += 1;
                    } else {
                        indexFirst = 0;
                        lst.addLast(current);
                    }
                }
            }
            output(lst);
        }

    }

    /**
     * print string output
     *
     * @param list
     */
    private static void output(LinkedList<Character> list) {
        StringBuilder sb = new StringBuilder();
        for (char current : list)
            sb.append(current);
        System.out.println(sb.toString());
    }
}
