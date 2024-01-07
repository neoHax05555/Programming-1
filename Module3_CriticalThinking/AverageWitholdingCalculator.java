import java.util.Scanner;

public class AverageWitholdingCalculator {
    public static void main(String[] args) {
        double income = 0;
        double taxWitheld = 0;
	double percentage = 0;
        String incomeInput = "";
        boolean incomeAccepted = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to The Average Tax Witholding Calculator.");
        System.out.println("enter your weekly income:");
        
        // Take input and verify is numeric and is value greater than 0.
        while (!incomeAccepted) {
            incomeInput = scanner.next();
            try {
                income = Double.parseDouble(incomeInput);
                if (income < 0) {
                    System.out.println("Invalid input: Please enter a value greater than 0");
                } else {
                    incomeAccepted = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Please enter a numeric value.");
            }
        }

        if (income < 500) {
            // Income less than $500: tax rate 10%
	    percentage = .10;
        } else if (income >= 500 && income < 1500) {
            // Incomes greater/equal to $500 and less than $1500: tax rate 15%
	    percentage = .15;
        } else if (income >= 1500 && income < 2500) {
            // Incomes greater/equal to $1500 and less than $2500: tax rate 20%
            percentage = .20;
        } else {
            // Assumed Incomes greater than/equal to $2500: tax rate 30%
            percentage = .30;
        }

	// Calculate witholdings.
	taxWitheld = income * percentage;

	System.out.println("\n");
        System.out.println(" With and income per week of: $" + income);
        System.out.println(" Your percentage of taxable income is: " + (percentage * 100) + "%");
        System.out.println(" Tax Witheld per week: $" + taxWitheld);
        System.out.println(" Tax Witheld per month: $" + (taxWitheld * 4));
    }
}

