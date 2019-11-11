/* Main.java
* UVa 10258 -- Contest Scoreboard
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    private static final Scanner SC = new Scanner(System.in);
    private static final int INCORRECT_SUBMISSION = 20;
    private static Contestant[] contestants;

    public static void main(String[] args) {
        SC.nextInt();
        ignoreLines(2); // Avoiding the \n problem in terminal and ignoring next blank line
        contestants = new Contestant[101];

        // While there are lines with the scores will read it
        while (SC.hasNextLine()) {
            String lineJudgeQueue = SC.nextLine();

            if (lineJudgeQueue.isEmpty()) {
                output();
                System.out.println();
                contestants = new Contestant[101];
                continue;
            }

            String[] tokensJudgeQueue = lineJudgeQueue.split(" ");
            int numberCon = Integer.parseInt(tokensJudgeQueue[0]);
            int problem = Integer.parseInt(tokensJudgeQueue[1]);
            int penalty = Integer.parseInt(tokensJudgeQueue[2]);
            char L = tokensJudgeQueue[3].charAt(0);

            // Check if the Contestant exists or not
            if (contestants[numberCon] == null) {
                contestants[numberCon] = new Contestant(numberCon);
            }

            Contestant current = contestants[numberCon];

            // If the key already exists is because was already solved
            if (current.solvedProblems.get(problem) != null) {
                continue;
            }

            // Incorrect question so +20 penalty
            if (L == 'I') {
                current.incorrectPenalty[problem] += INCORRECT_SUBMISSION;
            }
            /*
             * Correct answer so register the penalty on solvedProblems map with the number
             * of the problem as key
             */
            else if (L == 'C') {
                current.solvedProblems.put(problem, penalty + current.incorrectPenalty[problem]);
            }
        }

        output();
    }

    // Ignore the number of lines in terminal
    public static void ignoreLines(int numberOfLines) {
        for (int i = 0; i < numberOfLines; i++) {
            SC.nextLine();
        }
    }

    public static void output() {
        // Filter only the objects no nullables and puts inside a list
        ArrayList<Contestant> noNullablesContestant = new ArrayList<>();

        for (int i = 0; i < contestants.length; i++) {
            if (contestants[i] != null) {
                noNullablesContestant.add(contestants[i]);
            }
        }

        // Sorting by the rules specified in the class Contestant
        Collections.sort(noNullablesContestant);

        // Print the output result
        for (int i = 0; i < noNullablesContestant.size(); i++) {
            System.out.println(noNullablesContestant.get(i).toString());
        }
    }
}

// Contestants model representation of the contest
final class Contestant implements Comparable<Contestant> {
    public int num;
    public HashMap<Integer, Integer> solvedProblems; // key:problem & value:penalty
    public int[] incorrectPenalty;

    public Contestant(int numContestant) {
        this.num = numContestant;
        solvedProblems = new HashMap<>();
        incorrectPenalty = new int[10];
    }

    public int calculteTotalPenalty() {
        int result = 0;
        return this.solvedProblems.values().stream().map((value) -> value).reduce(result, Integer::sum);
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
        if (contestant == null || this.qntProblemsSolved() > contestant.qntProblemsSolved()) {
            return -1;
        }

        if (this.qntProblemsSolved() == contestant.qntProblemsSolved()
                && this.calculteTotalPenalty() < contestant.calculteTotalPenalty()) {
            return -1;
        }

        if (this.calculteTotalPenalty() == contestant.calculteTotalPenalty() && this.num < contestant.num) {
            return -1;
        }

        return 1;
    }
}
