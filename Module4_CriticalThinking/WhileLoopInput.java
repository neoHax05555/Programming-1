import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Alec McDaugale
 * @Date: 01-13-24
 * @Course: CSC320-1
 * <br><br>
 * Option #1: Looping Construct with Floating Point Numbers<br><br>
 * Write a program that utilizes a while-loop to read a set of five floating-point values from user input. Include code
 * to prevent an endless loop. Ask the user to enter the values, then print the following data:
 * ------------------------------------------------------------------------------------------
 * <ul>
 * <li>Total</li>
 * <li>Average</li>
 * <li>Maximum</li>
 * <li>Minimum</li>
 * <li>Interest on total at 20%</li>
 * </ul>
 * ------------------------------------------------------------------------------------------
 * Compile and submit your pseudocode, source code, and screenshots of the application executing the application, the
 * results and GIT repository in a single document.
 */
public class WhileLoopInput {
    private static final float INTEREST_RATE = 0.20f;

    public static void main(String[] args) {
        ArrayList<Float> inputValues = new ArrayList<>();
        String rawInput;
        boolean done = false;
        boolean invalid = false;
        int acceptedCount = 0;
        int invalidCount = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input a total of 5 numbers one by one.");
        do {
            try {
                System.out.println("Please input a floating point value.");
                rawInput = scanner.next();
                inputValues.add(Float.parseFloat(rawInput));
                acceptedCount++;
                invalidCount = 0;
                if (5 == acceptedCount) {
                    done = true;
                }
            } catch (NumberFormatException e) {
                invalidCount++;
                if (5 == invalidCount) {
                    done = true;
                    invalid = true;
                    System.out.println("5 Invalid attempts, aborting to avoid infinite looping.");
                } else {
                    System.out.println("Please input a valid floating point number. ex: 1.23");
                }
            }
        } while (!done);

        if (!invalid) {
            float total = 0;
            float average = 0;
            // init max and min to first value to ensure initialized for first comparison.
            float max = inputValues.get(0);
            float min = inputValues.get(0);
            float interest = 0;
            int count = 0;

            for (float value : inputValues) {
                count++;
                //Total
                total += value;
                //Maximum/Minimum
                max = (value > max) ? value : max;
                min = (value < min) ? value : min;
            }

            //Average
            average = total / count;
            //Interest on total at 20%
            interest = total * INTEREST_RATE;

            System.out.println();
            System.out.println("Here are the results:");
            System.out.println("Total:        " + total);
            System.out.println("Average:      " + average);
            System.out.println("Maximum:      " + max);
            System.out.println("Minimum:      " + min);
            System.out.println("Interest 20%: " + interest);
        } else {
            System.out.println("Please restart program and try again.");
        }
    }
}