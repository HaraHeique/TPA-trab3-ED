/* Main.java
* UVa 10258 -- Contest Scoreboard
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/
package uva10258;

import java.util.Scanner;
import java.util.HashMap;

public class Main {
    private static final Scanner SC = new Scanner(System.in);
    private static Contestant[] contestants;

    public static void main(String[] args) {
        int nCases = SC.nextInt();
        ignoreLines(2); // Avoiding the \n problem in terminal and ignoring next blank line
        contestants = new Contestant[101];
        
        // While there are lines with the scores will read it
        while (SC.hasNextLine()) {
            String lineJudgeQueue = SC.nextLine();
            
            if (lineJudgeQueue.isEmpty()) {
                continue;
            }
            
            String[] tokensJudgeQueue = lineJudgeQueue.split(" ");
            int numberCon = Integer.parseInt(tokensJudgeQueue[0]);
            int problem = Integer.parseInt(tokensJudgeQueue[1]);
            int penalty = Integer.parseInt(tokensJudgeQueue[2]);
            
            // Check if the Contestant exists or not
            if (contestants[numberCon] == null) {
                contestants[numberCon] = new Contestant(numberCon);  
            }
        }
   }
   
   // Ignore the number of lines in terminal
   public static void ignoreLines(int numberOfLines) {
       for (int i = 0; i < numberOfLines; i++) {
           SC.nextLine();
       }
   }
}

// Contestants model representation of the contest 
final class Contestant implements Comparable<Contestant>{
    public int num;
    public HashMap<Integer, Integer> solvedProblems; // key:problem & value:penalty

    public Contestant(int numContestant) {
        this.num = numContestant;
        solvedProblems = new HashMap<>();
    }

    public int calculteTotalPenalty() {
        int total = 0;
        this.solvedProblems.values().stream().map((value) -> value).reduce(total, Integer::sum);
        return total;
    }

    public int qntProblemsSolved() {
        return this.solvedProblems.size();
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.num, this.qntProblemsSolved(), this.calculteTotalPenalty());
    }

    @Override
    public int compareTo(Contestant contestant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
