/* Main.java
* UVa 10038 -- Jolly Jumpers
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
            boolean isJolly = true;
            for (int i = 0; i < line.size() - 1; i++) {
                int result = Math.abs(line.get(i) - line.get(i + 1));
                isJolly = isJolly && result > 0 && result < line.size();
                if (!isJolly)
                    break;
            }
            System.out.println(line);
            if (isJolly)
                System.out.println("Jolly");
            else
                System.out.println("Not jolly");
        }
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
