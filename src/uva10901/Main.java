/* Main.java
* UVa 10901 -- Ferry Loading III
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Main {

    private final static String LEFT = "left";
    private final static String RIGHT = "right";

    private static Scanner scanner;

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int casesLength = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < casesLength; i++) {
            List<Integer> firstLine = getLineInt(scanner.nextLine());
            int n = firstLine.get(0);
            int t = firstLine.get(1);
            int m = firstLine.get(2);

            Queue<int[]> l = new LinkedList<>();
            Queue<int[]> r = new LinkedList<>();

            fillQueues(m, l, r);

            for (int time : getOutput(n, t, m, l, r)) {
                System.out.println(time);
            }
            if (i + 1 < casesLength) {
                System.out.printf("\n");
            }
        }
    }

    /**
     * Fill left and right queue
     *
     * @param m
     * @param leftQueue
     * @param rightQueue
     */
    public static void fillQueues(int m, Queue<int[]> leftQueue, Queue<int[]> rightQueue) {
        for (int i = 0; i < m; i++) {
            String[] buf = Main.scanner.nextLine().split(" ");
            int carTime = Integer.parseInt(buf[0]);
            String bank = buf[1];
            int[] car = new int[2];
            car[0] = carTime;
            car[1] = i;
            if (isEquals(bank, RIGHT)) {
                rightQueue.add(car);
            } else {
                leftQueue.add(car);
            }
        }
    }

    /**
     * calculate time of two queues and returns a output array
     *
     * @param n
     * @param t
     * @param m
     * @param left
     * @param right
     * @return
     */
    public static int[] getOutput(int n, int t, int m, Queue<int[]> left, Queue<int[]> right) {
        int[] output = new int[m];
        int time = 0;
        String currentBank = LEFT;
        Queue<int[]> aux1, aux2;

        while (!left.isEmpty() || !right.isEmpty()) {

            if (isEquals(currentBank, LEFT)) {
                aux1 = left;
                aux2 = right;
            } else {
                aux1 = right;
                aux2 = left;
            }

            int enqueuedCount = 0;

            while (enqueuedCount < n && !aux1.isEmpty() && aux1.peek()[0] <= time) {
                int[] car = aux1.remove();
                output[car[1]] = time + t;
                enqueuedCount++;
            }

            if (enqueuedCount != 0 || !aux2.isEmpty() && aux2.peek()[0] <= time) {
                time += t;
                currentBank = toggleBank(currentBank);
            } else if (aux1.isEmpty() || !aux2.isEmpty() && aux2.peek()[0] < aux1.peek()[0]) {
                time = aux2.peek()[0] + t;
                currentBank = toggleBank(currentBank);
            } else {
                time = aux1.peek()[0];
            }
        }

        return output;
    }

    /**
     * if string a is equals string b apllying trim
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isEquals(String a, String b) {
        return a.trim().equals(b.trim());
    }

    /**
     * toggle bank state
     *
     * @param current
     * @return
     */
    public static String toggleBank(String current) {
        return current == LEFT ? RIGHT : LEFT;
    }

    /**
     * get scond line and map to Integer
     *
     * @param line
     * @return
     */
    public static List<Integer> getLineInt(String line) {
        List<String> res = Arrays.asList(line.split(" "));
        return res.stream().map(e -> Integer.parseInt(e)).collect(Collectors.toList());
    }

}
