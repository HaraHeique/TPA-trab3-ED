/* Main.java
* UVa 10132 -- File Fragmentation
* Autores: David Vilaça, Harã Heique e Larissa Motta
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {

    private static final BufferedReader SC = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        // First reads the line that represents the number of cases of the dataset
        int n = Integer.parseInt(SC.readLine());
        ignoreLines(1);

        for (int i = 0; i < n; i++) {
            // Add the fragments in memory list structure
            ArrayList<String> listFragments = readBlock();

            // Get the length of final file
            int lengthOfFinalFile = getLengthOfFinalFile(listFragments);

            // Get possibles solutions
            SortedMap<String, Integer> possibleResults = getPossiblesSolutions(listFragments, lengthOfFinalFile);

            // Print solution

            for (String key : possibleResults.keySet()) {
                if (possibleResults.get(key) >= (listFragments.size() / 2)) {
                    System.out.println(key);
                    break;
                }
            }
            // Print a blank line
            if (i != n - 1) {
                System.out.println();
            }
        }

        SC.close();
        System.exit(0);
    }

    // Ignore the number of lines in terminal
    public static void ignoreLines(int numberOfLines) throws IOException {
        for (int i = 0; i < numberOfLines; i++) {
            SC.readLine();
        }
    }

    // Returns the fragments in memory list structure
    public static ArrayList<String> readBlock() throws IOException {
        ArrayList<String> listFragments = new ArrayList();
        while (true) {
            String fragment = SC.readLine();
            if (fragment == null || fragment.isEmpty()) {
                break;
            }
            listFragments.add(fragment);
        }
        return listFragments;
    }

    // Returns the length of final file based on the min and max fragment
    public static int getLengthOfFinalFile(ArrayList<String> listFragments) {
        int min = listFragments.get(0).length();
        int max = min;
        for (int i = 1; i < listFragments.size(); i++) {
            int length = listFragments.get(i).length();
            if (length > max) {
                max = length;
            }
            if (min > length) {
                min = length;
            }
        }
        return min + max;
    }

    // Returns a TreeMap with the possibles results
    public static SortedMap<String, Integer> getPossiblesSolutions(ArrayList<String> listFragments, int lengthOfFile) {
        SortedMap<String, Integer> possibleResults = new TreeMap();
        for (int j = 0; j < listFragments.size(); j++) {
            for (int k = 0; k < listFragments.size(); k++) {
                if (k != j) {
                    String possibility = listFragments.get(j) + listFragments.get(k);
                    // Verify if the possibility has the length of final file
                    if (possibility.length() == lengthOfFile) {
                        Integer count = possibleResults.get(possibility);
                        if (count != null) {
                            possibleResults.put(possibility, count + 1);
                        } else {
                            possibleResults.put(possibility, 1);
                        }
                    }
                }

            }
        }
        return possibleResults;
    }
}
