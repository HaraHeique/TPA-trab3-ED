/* Main.java
* UVa 11340 -- Newspaper
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    // Main function
    public static void main(String[] args) {
        int n = scanner.nextInt();
        float money_paid = 0.00f;

        // Looping through the number of tests
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            ArrayList<Character> paid_chars = new ArrayList<>();
            ArrayList<Integer> paid_char_values = new ArrayList<>();

            scanner.nextLine(); // Avoiding the \n problem in console

            /*
             * Looping through the paid characters and storing inside arrays to check how
             * much will pay to the current article
             */
            for (int j = 0; j < k; j++) {
                String[] split_line = scanner.nextLine().split(" ");
                paid_chars.add(split_line[0].charAt(0));
                paid_char_values.add(Integer.parseInt(split_line[1]));
            }

            int m = scanner.nextInt();
            scanner.nextLine(); // Avoiding the \n problem in console

            /*
             * Looping through the article and calculate how much money publisher must pay
             * for the article
             */
            for (int l = 0; l < m; l++) {
                String line_article = scanner.nextLine();

                // Search for the index of character and realizes the sum if find it
                for (int c = 0; c < line_article.length(); c++) {
                    int indexOfChar = paid_chars.indexOf(line_article.charAt(c));
                    if (indexOfChar != -1) {
                        money_paid += (float) (paid_char_values.get(indexOfChar) / 100.00f);
                    }
                }
            }

            System.out.printf("%.2f$\n", money_paid);
            money_paid = 0.0f;
        }
    }
}
