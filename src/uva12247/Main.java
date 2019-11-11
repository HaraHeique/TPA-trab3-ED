/* Main.java
* UVa 10130 -- Jollo
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static Scanner scanner;
    private static int[] prince, princess;
    static boolean[] princePlayed, princessPlayed;

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String buf = scanner.nextLine();
        int[] line = getLine(buf);
        while (!isLineZeros(line)) {
            int answer = -1;
            princess = new int[3];
            prince = new int[3];
            princePlayed = new boolean[3];
            princessPlayed = new boolean[3];

            princess[0] = line[0];
            princess[1] = line[1];
            princess[2] = line[2];
            prince[0] = line[3];
            prince[1] = line[4];

            // System.out.println("princess");
            // printArrDebug(princess);
            // System.out.println("prince");
            // printArrDebug(prince);

            for (int i = 1; i < 53 && answer == -1; i++) {
                boolean isValid = true;
                prince[2] = -1;
                for (int j = 0; j < 3; j++) {
                    isValid = isValid && princess[j] != i && prince[j] != i;
                }
                if (isValid) {
                    prince[2] = i;
                    // 0 == prince player
                    answer = worst(0, 0, -1) ? i : -1;
                }
            }

            System.out.println(answer);

            buf = scanner.nextLine();
            line = getLine(buf);
            // System.out.println("buf");
            // System.out.println(buf);
        }

    }

    /**
     * recursive get the worst play
     *
     * @param player
     * @param lost
     * @param prev
     * @return
     */
    static boolean worst(int player, int lost, int prev) {
        if (lost > 1) {
            return false;
        }
        boolean ret = true;
        for (int i = 0; i < 3; i++) {
            if (player == 0 && !princePlayed[i]) { // prince player
                princePlayed[i] = true;
                ret = ret && worst(1, lost, prince[i]);
                princePlayed[i] = false;
            }
            if (player == 1 && !princessPlayed[i]) { // princess player
                princessPlayed[i] = true;
                int newLost = prev < princess[i] ? lost + 1 : lost;
                ret = ret && worst(0, newLost, -1);
                princessPlayed[i] = false;
            }
        }
        return ret;
    }

    /**
     * get all line maped to int from string input
     *
     * @param line
     * @return
     */
    public static int[] getLine(String line) {
        List<String> res = Arrays.asList(line.split(" "));
        int[] l = new int[5];
        for (int i = 0; i < 5; i++) {
            l[i] = Integer.parseInt(res.get(i));
        }
        return l;
    }

    /**
     * verify if all line elements is zero
     *
     * @param line
     * @return
     */
    public static boolean isLineZeros(int[] line) {
        for (Integer element : line) {
            if (element != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * debug function to print
     *
     * @param arr
     */
    public static void printArrDebug(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                System.out.print("?");
            else
                System.out.print(arr[i]);
            if (i + 1 < arr.length)
                System.out.print(", ");
        }
        System.out.println();
    }
}
