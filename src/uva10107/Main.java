/* Main.java
* UVa 10107 - What is the Median?
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/
package uva10107;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static Scanner scanner;

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        ArrayList<Integer> lstValues = new ArrayList<>();
        while (scanner.hasNext()) {
            int value = scanner.nextInt();
            lstValues.add(value);
            Collections.sort(lstValues);
            
            int half = (lstValues.size() / 2);
            
            if(lstValues.size() % 2 == 0){
                System.out.println((lstValues.get(half - 1) + lstValues.get(half)) / 2);
            }
            else{
                System.out.println(lstValues.get(half));
            }    
        }
    }
}
