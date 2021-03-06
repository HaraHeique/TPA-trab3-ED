/* Main.java
* UVa 00978 -- Lemmings Battle!
* Autores: David Vilaça, Harã Heique e Larissa Motta
 */

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        int nCases = SC.nextInt();
        ignoreLines(1);

        for (int i = 1; i <= nCases; i++) {
            String[] tokensInfoGame = SC.nextLine().split(" ");
            int nBattlefield = Integer.parseInt(tokensInfoGame[0]);
            int nLemmingGreen = Integer.parseInt(tokensInfoGame[1]);
            int nLemmingBlue = Integer.parseInt(tokensInfoGame[2]);

            // Creating a queue to each Lemming Green Army soldier
            PriorityQueue<Lemming> greenArmy = createLemmingArmy(nLemmingGreen);

            // Creating a queue to each Lemming Blue Army soldier
            PriorityQueue<Lemming> blueArmy = createLemmingArmy(nLemmingBlue);

            /*
             * Perfomances a battle until the end, wich means just one or any army has
             * elements
             */
            battle(greenArmy, blueArmy, nBattlefield);

            // Show the winner of the current war case
            showWinner(greenArmy, blueArmy);

            if (i < nCases) {
                System.out.println();
            }
        }
    }

    // Creates a Lemming Army
    private static PriorityQueue<Lemming> createLemmingArmy(int qntLemmingArmy) {
        PriorityQueue<Lemming> army = new PriorityQueue();

        while (qntLemmingArmy-- > 0) {
            int currentPower = SC.nextInt();
            ignoreLines(1);

            /*
             * Creates a Leeming and add into a queue based in his priority, wich is the
             * greater power
             */
            army.add(new Lemming(currentPower));
        }

        return army;
    }

    private static void battle(PriorityQueue<Lemming> greenArmy, PriorityQueue<Lemming> blueArmy, int nBattles) {
        if (nBattles <= 0) {
            return;
        }

        // Fight until one of them or both are dead
        while (!greenArmy.isEmpty() && !blueArmy.isEmpty()) {
            int nBattlesCurrent = nBattles;
            TreeSet<Lemming> greenBattlefieldArmy = new TreeSet();
            TreeSet<Lemming> blueBattlefieldArmy = new TreeSet();

            while (nBattlesCurrent-- > 0) {
                // Retrieves the next soldiers for the battle
                Lemming greenSoldier = greenArmy.remove();
                Lemming blueSoldier = blueArmy.remove();

                // Fight with each other and one of them or both will be in dead state
                greenSoldier.fight(blueSoldier);

                // Adds the green soldier with the difference power
                if (!greenSoldier.isDead) {
                    greenBattlefieldArmy.add(greenSoldier);
                }
                // Adds the blue soldier with the difference power
                if (!blueSoldier.isDead) {
                    blueBattlefieldArmy.add(blueSoldier);
                }

                // Check if at least one of the armies is all down while battlefield exists
                if (greenArmy.isEmpty() || blueArmy.isEmpty()) {
                    break;
                }
            }

            // Adds the soldiers in the real army after the battlefield ends
            if (!greenBattlefieldArmy.isEmpty()) {
                greenArmy.addAll(greenBattlefieldArmy);
            }

            if (!blueBattlefieldArmy.isEmpty()) {
                blueArmy.addAll(blueBattlefieldArmy);
            }
        }
    }

    private static void showWinner(PriorityQueue<Lemming> greenArmy, PriorityQueue<Lemming> blueArmy) {
        if (greenArmy.isEmpty() && blueArmy.isEmpty()) {
            System.out.println("green and blue died");
            return;
        }

        if (blueArmy.isEmpty()) {
            System.out.println("green wins");

            while (!greenArmy.isEmpty()) {
                System.out.println(greenArmy.remove().toString());
            }
            return;
        }

        if (greenArmy.isEmpty()) {
            System.out.println("blue wins");

            while (!blueArmy.isEmpty()) {
                System.out.println(blueArmy.remove().toString());
            }
        }
    }

    // Ignore the number of lines in terminal
    private static void ignoreLines(int numberOfLines) {
        for (int i = 0; i < numberOfLines; i++) {
            SC.nextLine();
        }
    }
}

final class Lemming implements Comparable<Lemming> {

    int power;
    boolean isDead;

    public Lemming(int power) {
        this.power = power;
    }

    public void fight(Lemming enemy) {
        int myPower = this.power - enemy.power;
        int enemyPower = enemy.power - this.power;

        if (myPower < 0) {
            this.isDead = true;
        } else if (myPower > 0) {
            enemy.isDead = true;
        } else {
            this.isDead = enemy.isDead = true;
        }

        this.power = myPower;
        enemy.power = enemyPower;
    }

    @Override
    public int compareTo(Lemming l) {
        if (this.power > l.power) {
            return -1;
        }

        return 1;
    }

    @Override
    public String toString() {
        return "" + this.power;
    }
}
