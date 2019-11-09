/* Main.java
* UVa 11926 -- Multitasking
* Autores: David Vilaça, Harã Heique e Larissa Motta
 */
package uva11926;

import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final int TOTAL_MINUTES = 1000000;

    public static void main(String[] args) {
        boolean hasAnyConflict = false;

        // Iterates over until finds a 0 0 line input
        while (SC.hasNextLine()) {
            String[] numberOfTasksTokens = SC.nextLine().split(" ");

            if (!hasNextTask(numberOfTasksTokens)) {
                break;
            }

            int qntOneTimeTask = Integer.parseInt(numberOfTasksTokens[0]);
            int qntRepeatingTask = Integer.parseInt(numberOfTasksTokens[1]);

            /* Represents the minutes of the problem. Each position represents 
            * a minute. If the position equals 0 then can be allocated. Otherwise
            cannot, wich means there is a conflict
             */
            int[] minutes = new int[TOTAL_MINUTES + 10];

            // First check if exists at least one conflict with one time tasks
            hasAnyConflict = hasConflictOneTimeTasks(qntOneTimeTask, minutes);

            // Second check if exists at least one conflict with reapeating tasks
            if (!hasAnyConflict) {
                hasAnyConflict = hasConflictRepeatingTasks(qntRepeatingTask, minutes);
            } else {
                ignoreLines(qntRepeatingTask);
            }

            // Prints if there is or not a conflict
            output(hasAnyConflict);
        }
    }

    private static boolean hasNextTask(String[] nTasksTokens) {
        return nTasksTokens[0].charAt(0) != '0' || nTasksTokens[1].charAt(0) != '0';
    }

    // Check if there is conflict, ie, if there is overlap of one time tasks
    private static boolean hasConflictOneTimeTasks(int qntTasks, int[] minutesTasksTime) {
        for (int i = 1; i <= qntTasks; i++) {
            Task currentTask = getTask(SC.nextLine());

            /* Iterates over time array and puts 1 to each position minute 
            * from start to end task time. If position already has 1 value
            * means there is a conflict. Otherwise there isn't.
             */
            for (int min = currentTask.start; min < currentTask.end; min++) {
                if (minutesTasksTime[min] == 1) {
                    // If finds any conflict reads the rest of lines to go to the next task
                    ignoreLines(qntTasks - i);
                    return true;
                }

                minutesTasksTime[min] = 1;
            }
        }

        return false;
    }

    // Check if there is conflict, ie, if there is overlap of one time tasks
    private static boolean hasConflictRepeatingTasks(int qntTasks, int[] minutesTasksTime) {
        for (int i = 1; i <= qntTasks; i++) {
            Task currentTask = getTask(SC.nextLine());

            /* Iterates over time array and puts 1 to each position minute 
            * from start to end task time. If position already has 1 value
            * means there is a conflict. Otherwise there isn't.
             */
            while (true) {
                for (int min = currentTask.start; min < currentTask.end; min++) {
                    if (minutesTasksTime[min] == 1) {
                        // If finds any conflict reads the rest of lines to go to the next task
                        ignoreLines(qntTasks - i);
                        return true;
                    }
                    
                    minutesTasksTime[min] = 1;
                }

                if (currentTask.end == TOTAL_MINUTES) {
                    break;
                }
                
                currentTask.setInterval(TOTAL_MINUTES);
            }
        }

        return false;
    }

    // Returns a object Task to represents one time tasks or repeating tasks
    private static Task getTask(String dataString) {
        String[] tokensTime = dataString.split(" ");
        int start = Integer.parseInt(tokensTime[0]);
        int end = Integer.parseInt(tokensTime[1]);

        if (tokensTime.length == 3) {
            int interval = Integer.parseInt(tokensTime[2]);

            return new Task(start, end, interval);
        }

        return new Task(start, end);
    }

    // Prints out the current result
    private static void output(boolean hasConflict) {
        if (hasConflict) {
            System.out.println("CONFLICT");
        } else {
            System.out.println("NO CONFLICT");
        }
    }

    // Ignore the number of lines in terminal
    public static void ignoreLines(int numberOfLines) {
        for (int i = 0; i < numberOfLines; i++) {
            SC.nextLine();
        }
    }
}

final class Task {

    int start;
    int end;
    int interval;

    public Task(int start, int end) {
        this.start = start;
        this.end = end;
        this.interval = 0;
    }

    public Task(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public int spendedTime() {
        return this.end - this.start;
    }

    // Negative time limit means there is no time limit. Otherwise has a time limit
    public void setInterval(int maxlimitTime) {
        this.start += this.interval;
        this.end += this.interval;

        if (maxlimitTime > 0) {
            this.start = (this.start > maxlimitTime) ? maxlimitTime : this.start;
            this.end = (this.end > maxlimitTime) ? maxlimitTime : this.end;
        }
    }
}