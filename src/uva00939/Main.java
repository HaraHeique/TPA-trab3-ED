/* Main.java
* UVa 00939 -- Genes
* Autores: David Vilaça, Harã Heique e Larissa Motta
 */
package uva00939;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final SortedMap<String, Person> RESULT = new TreeMap();
    private static final String DOMINANT = "dominant";
    private static final String RECESSIVE = "recessive";
    private static final String NON_EXISTENT = "non-existent";

    public static void main(String[] args) {
        //First reads the line that represents the number of lines of the dataset
        int n = SC.nextInt();
        ignoreLines(1);
        for (int i = 0; i < n; i++) {
            String[] tokens_line = SC.nextLine().split(" ");
            String personName = tokens_line[0];

            // Check if the token of second positions is a person or a gene
            if (isGene(tokens_line[1])) {
                if (RESULT.containsKey(personName)) {
                    RESULT.get(personName).gene = tokens_line[1];
                } else {
                    RESULT.put(personName, new Person(personName, tokens_line[1]));
                }
            } else {
                String parent = personName;
                personName = tokens_line[1];

                if (!RESULT.containsKey(parent)) {
                    RESULT.put(parent, new Person(parent));
                }
                if (!RESULT.containsKey(personName)) {
                    RESULT.put(personName, new Person(personName));
                }
                RESULT.get(personName).parents.add(RESULT.get(parent));

            }
        }
        output();
    }

// Prints out the RESULT map values sorted by name
    public static void output() {
        RESULT.keySet().forEach((key) -> {
            System.out.println(key + " " + RESULT.get(key).getGene());
        });
    }

// Verify if a string is a gene
    public static boolean isGene(String value) {
        return value.equals(DOMINANT) || value.equals(RECESSIVE) || value.equals(NON_EXISTENT);
    }

    // Ignore the number of lines in terminal
    public static void ignoreLines(int numberOfLines) {
        for (int i = 0; i < numberOfLines; i++) {
            SC.nextLine();
        }
    }
}

final class Person {

    private static final String DOMINANT = "dominant";
    private static final String RECESSIVE = "recessive";
    private static final String NON_EXISTENT = "non-existent";

    public String name;
    public String gene;
    public ArrayList<Person> parents = new ArrayList();

    public Person(String name, String gene) {
        this.name = name;
        this.gene = gene;
    }

    public Person(String name) {
        this.name = name;
        this.gene = null;
    }

    public String getGene() {
        if (this.gene == null) {
            this.gene = identifyGene(this.parents.get(0).getGene(), this.parents.get(1).getGene());
        }
        return this.gene;
    }

    // Identifies wich gene the person has
    private static String identifyGene(String parentGene1, String parentGene2) {
        // If they are equal means that the parents have the same gene
        if (parentGene2.equals(parentGene1)) {
            return parentGene1;
        }

        // Verify if the parents have a gene
        boolean dominant = parentGene1.equals(DOMINANT) || parentGene2.equals(DOMINANT);
        boolean recessive = parentGene1.equals(RECESSIVE) || parentGene2.equals(RECESSIVE);

        // Check the correct gene of the current person based on his parents
        if (dominant && recessive) {
            return DOMINANT;
        }

        if (dominant && !recessive) {
            return RECESSIVE;
        }
        return NON_EXISTENT;
    }
}
