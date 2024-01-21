import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Alec McDaugale
 * @Date: 01-13-24
 * @Course: CSC320-1
 * <br><br>
 * Option #1: Get Weekly Tempuatures<br><br>
 * Develop a Java program that will store data in the form of daily average temperatures for one week. Store the day and
 * average temperature in two different arraylists. Your program should prompt the user for the day of the week (Monday
 * through Sunday) and display both the day and temperature for each day. If "week" is entered, the output for your
 * program should provide the temperature for each day and the weekly average. Use the looping and decision constructs
 * in combination with the arrays to complete this assignment.
 *
 * ------------------------------------------------------------------------------------------
 * Compile and submit your pseudocode, source code, and screenshots of the application executing the application, the
 * results and GIT repository in a single document.
 */
public class WeeklyTempuatures {
    /**
     * Initial state for weekdayIndex, represents an invalid value to use to determine if weekdayIndex was set.
     */
    private static final int INVALID_WEEKDAY = -1;
    /**
     * Max weekday, above range of valid indexes to represent all values for the week.
     */
    private static final int ALL_WEEKDAY = 7;

    public static void main(String[] args) {
        ArrayList<String> weekDay = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednessday",
                "Thursday", "Friday", "Saturday", "Sunday"));
        ArrayList<Double> tempuature = new ArrayList<>(Arrays.asList(55.0, 77.2, 65.5, 33.4, 2.0, -4.1, 25.2));
        boolean done = false;
        // Default to -1 until set.
        int weekdayIndex = INVALID_WEEKDAY;
        String rawInput;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input a day of the week to get the average tempuature for that day, " +
                "\nor enter 'week' for the average tempuature this week.");
        // Accept user input.
        do {
            rawInput = scanner.next();
            if ("week".equalsIgnoreCase(rawInput)) {
                weekdayIndex = ALL_WEEKDAY;
            } else {
                for (String day : weekDay) {
                    if (day.equalsIgnoreCase(rawInput)) {
                        // If found get the index of the given day.
                        weekdayIndex = weekDay.indexOf(day);
                    }
                }
            }

            if (INVALID_WEEKDAY == weekdayIndex) {
                System.out.println("Input was invalid. Please enter a day of the week (Monday -> Sunday) or the word \"week\".");
            }
        } while (INVALID_WEEKDAY == weekdayIndex);

        // Print results.
        if (ALL_WEEKDAY == weekdayIndex) {
            // If 'week' was entered, we need to pring averages.
            double average = 0;
            double weeklySum = 0;
            double temp;

            // Sum temps for each day.
            for (String day : weekDay) {
                temp = tempuature.get(weekDay.indexOf(day));
                weeklySum += temp;
                System.out.printf("%-11s %2.2f\n", day, temp);
            }
            // Calculate average.
            average = weeklySum/ALL_WEEKDAY;
            System.out.println("Weekly Average: " + average);
        } else {
            // Else a weekday was entered, so print that data out for that index.
            System.out.printf("%-11s %2.2f\n", weekDay.get(weekdayIndex), tempuature.get(weekdayIndex));
        }
    }
}