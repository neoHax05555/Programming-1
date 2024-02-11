package com.alec.portfolio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String DIR_NAME = "tmp";
    private static final String FILE_NAME = "VehicleInventory.txt";

    public static void main(String[] args) {
        Vehicle firstCar = null;
        VehicleInventory vehicleInventory;
        boolean success = false;

        // #1 & #2
        /* Initialize a car using builder method. The builder method calls a parameterized constructor
         * private to the Vehicle class.
         */
        try {
            firstCar = new Vehicle.VehicleBuilder("Chevrolet", "Impala", 2017)
                    .setColor("Green")
                    .setMileage(135000)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid vehicle format.\n" + e);
        }
        /* Check if the first car was created successfully and init inventory with the first car,
         * Else if unsuccessful, init with empty inventory.
         */
        if (null != firstCar) {
           vehicleInventory = new VehicleInventory(firstCar);
        } else {
            vehicleInventory = new VehicleInventory();
        }
        // Print what we have in array to user.
        printStringArray(vehicleInventory.listVehicleInfo());

        // #3
        // Remove vehicle we created @ index 0
        success = vehicleInventory.removeVehicle(0);
        // Print result.
        System.out.println("Remove Vehicle: " + (success ? "Successful" : "Failed"));

        // #4
        success = vehicleInventory.addVehicle("Ram", "2500", "Silver", 2017, 99000);
        // Print result.
        System.out.println("Add Vehicle: " + (success ? "Successful" : "Failed"));
        // Print what we have in array to user.
        printStringArray(vehicleInventory.listVehicleInfo());

        // #5
        success = vehicleInventory.updateVehicle(0, "Ram", "2500", "Red", 2018, 199000);
        // Print result.
        System.out.println("Update Vehicle: " + (success ? "Successful" : "Failed"));
        // Print what we have in array to user.
        printStringArray(vehicleInventory.listVehicleInfo());

        // #6
        System.out.println("Would you like to print the current inventory to a file? [y/n]");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        int invalidCounter = 5;
        success = false; // Set success to false to init for loop.

        do {
            switch (response) {
                case "y":
                case "Y":
                    printToFile(vehicleInventory.listVehicleInfo());
                    success = true;
                    break;
                case "n":
                case "N":
                    System.out.println("Not printing vehicleInfo to file.");
                    success = true;
                    break;
                default:
                    if (invalidCounter > 0) {
                        System.out.println("Invalid input, plese respond with either 'y' or 'n'.");
                        invalidCounter--;
                    } else {
                        System.out.println("Invalid input, max attempts reached, forcing exit.");
                        success = true;
                    }
            }
        } while (!success);

        // Exit once complete.
        System.exit(0);
    }

    /**
     * Prints an element of a String array on a line.
     *
     * @param strings String array to print.
     */
    private static void printStringArray(String[] strings) {
        for (String string : strings) {
            System.out.println(string);
        }
    }

    /**
     * System independent method to write all Vehicles in inventory to a file. In the
     * current working directory we will create a directory and file called
     * tmp/VehicleInventory.txt
     *
     * @param vehicleInventory A string format of vehicle inventory to print to file.
     *
     * @return true  - if write was successful.
     *         false - if failed to write to file.
     */
    private static boolean printToFile(String[] vehicleInventory) {
        /*
         * Get cwd and append the dirname, should be potable across systems,
         * i`m on linux you may be on windows based on location you posted.
         * (no C:\ drive in linux)
         */
        File fileLocation = new File(new File(".").getAbsolutePath(), DIR_NAME);
        File file = new File(fileLocation, FILE_NAME);
        boolean success = false;

        // Create dir if DNE.
        if (fileLocation.mkdir()) {
            if (!fileLocation.exists()) {
                // Already init to false, so a failure to create dir will return false.
                return success;
            }
        }

        try (FileOutputStream fos = new FileOutputStream(file)) {
            for (String car : vehicleInventory) {
                // Append newline to end of each string, else all on one line.
                fos.write((car + "\n").getBytes());
                fos.flush();
            }
            success = true;
        } catch (IOException e) {
            System.out.println("Failed to write output.\n" + e);
        }

        return success;
    }
}
