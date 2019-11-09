/* Main.java
* UVa 10264 -- The Most Potent Corner
* Autores: David Vilaça, Harã Heique e Larissa Motta
 */
package uva10264;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        while (SC.hasNextLine()) {
            int n = SC.nextInt();
            ignoreLines(1);
            
            List<Integer> weightCorners = takeWeightCorners(n);
            List<Integer> potencyCorners = calculateCornersPotency(weightCorners, n);
            int maxSumPotency = maxPotencySumNeighborCorners(potencyCorners, n);
            
            System.out.println(maxSumPotency);
        }
    }
    
    // Get all weight corners based on dimension of the cube
    public static List<Integer> takeWeightCorners(int n) {
        ArrayList<Integer> weightCorners = new ArrayList();
        
        for (int i = 0; i < Math.pow(2, n); i++) {
            weightCorners.add(SC.nextInt());
            ignoreLines(1);
        }
        
        return weightCorners;
    }
    
    // Calculate the potency of each corners based on their weights
    public static List<Integer> calculateCornersPotency(List<Integer> weightCorners, int numberOfNeighbor) {
        ArrayList<Integer> potencyCorners = new ArrayList();
        
        // Iterates over the corners weight
        for (int i = 0; i < weightCorners.size(); i++) {
            int potenciesSum = 0;
            
            /* Calculate the weight sum of each corner 
            * i = corner position; j = weight corner neighbor
            */
            for (int j = 0; j < numberOfNeighbor; j++) {
                int indexNeighbor = i ^ (1 << j);
                potenciesSum += weightCorners.get(indexNeighbor);
            }
            potencyCorners.add(potenciesSum);
        }
        
        return potencyCorners;
    }
    
    // Returns the max sum of potencies of two neighbor corners
    public static int maxPotencySumNeighborCorners(List<Integer> potencyCorners, int numberOfNeighbor) {
        ArrayList<Integer> potenciesSumNeighbor = new ArrayList();
               
        for (int i = 0; i < potencyCorners.size(); i++) {
            for (int j = 0; j < numberOfNeighbor; j++) {
                int indexCurrentCorner = i;
                int indexNeighbor = i ^ (1 << j);
                potenciesSumNeighbor.add(potencyCorners.get(indexCurrentCorner) + potencyCorners.get(indexNeighbor));
            }
        }
        
        return Collections.max(potenciesSumNeighbor);
    }
    
    // Ignore the number of lines in terminal
    public static void ignoreLines(int numberOfLines) {
        for (int i = 0; i < numberOfLines; i++) {
            SC.nextLine();
        }
    }
}