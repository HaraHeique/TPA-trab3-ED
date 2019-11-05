/* Main.java
* UVa 10258 -- Contest Scoreboard
* Autores: David Vilaça, Harã Heique e Larissa Motta
*/
package uva10258;

import java.util.Scanner;
import java.util.HashMap;

public class Main {
	private static Scanner scanner = new Scanner(System.in);  
	
	public static void main(String[] args) {
		
	}

}

// Contestants model representation of the contest 
final class Contestant {
	public int num;
	public HashMap<Integer, Integer> solvedProblems; // key:problem & value:penalty
	
	public Contestant(int numContestant) {
		this.num = numContestant;
		solvedProblems = new HashMap<>();
	}
	
	public int calculteTotalPenalty() {
		int total = 0;
		for (int value : this.solvedProblems.values()) {
		    total += value;
		}
		
		return total;
	}
	
	public int qntProblemsSolved() {
		return this.solvedProblems.size();
	}
}