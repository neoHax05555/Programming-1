package com.alec.portfolio;

import java.util.ArrayList;

/**
 * Vehicle inventory class, vehicles in inventory are stored in an ArrayList called garage.
 */
public class VehicleInventory {
    private final ArrayList<Vehicle> garage;

    /**
     * Default constructor.
     */
    VehicleInventory() {
         this.garage = new ArrayList<>();
    }

    /**
     * Constructor to accept a Vehicle to init in garage.
     *
     * @param vehicle The vehicle to init in new garage.
     */
    VehicleInventory(Vehicle vehicle) {
        this.garage = new ArrayList<>();
        garage.add(vehicle);
    }

    /**
     * Constructor to init garage with an arraylist of vehicles.
     *
     * @param vehicles An arraylist of vehicles to add to garage.
     */
    VehicleInventory(ArrayList<Vehicle> vehicles) {
        this.garage = vehicles;
    }

    /**
     * Add a new vehicle to the garage.
     *
     * @param make - The company who produced this car.
     * @param model - The name to identify the vehicle the company produced.
     * @param color Non null String to represent a color.
     * @param year - The year the vehicle was manufactured, must be after 1886.
     * @param mileage If invalid -1, else a non-negative value representing the vehicles' mileage.
     *
     * @return true  - if vehicle successfully added.
     *         false - if we failed to add the vehicle to garage.
     */
    public boolean addVehicle(String make, String model, String color, int year, int mileage) {
        boolean success = false;
        Vehicle.VehicleBuilder vehicle;
        try {
            vehicle = new Vehicle.VehicleBuilder(make, model, year);
            if (null != color && !color.isEmpty()) {
                vehicle.setColor(color);
            }
            vehicle.setMileage(mileage);
            // Success always false unless this line returns true.
            success = this.addVehicle(vehicle.build());
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add vehicle.\n" + e);
        }

        return success;
    }

    /**
     * Add a new vehicle to the garage.
     *
     * @param vehicle The Vehicle object to add.
     *
     * @return Always true.
     */
    public boolean addVehicle(Vehicle vehicle) {
        //Always true
        return garage.add(vehicle);
    }

    /**
     * Remove a vehicle at a given index from the garage.
     *
     * @param index Index of vehicle to remove.
     *
     * @return true  - if vehicle successfully removed.
     *         false - if we failed to remove the vehicle from garage.
     */
    public boolean removeVehicle(int index) {
        boolean success = false;
        try {
            success = removeVehicle(garage.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index was out of bounds.\n" + e);
        }
        return success;
    }

    /**
     * Remove a vehicle with a parameterized approach.
     *
     * @param make - The company who produced this car.
     * @param model - The name to identify the vehicle the company produced.
     * @param color Non null String to represent a color.
     * @param year - The year the vehicle was manufactured, must be after 1886.
     * @param mileage If invalid -1, else a non-negative value representing the vehicles' mileage.
     *
     * @return true  - if vehicle successfully removed.
     *         false - if we failed to remove the vehicle from garage.
     */
    public boolean removeVehicle(String make, String model, String color, int year, int mileage) {
        boolean success = false;
        Vehicle.VehicleBuilder vehicleBuilder;

        try {
            vehicleBuilder = new Vehicle.VehicleBuilder(make, model, year);
            if (null != color && !color.isEmpty()) {
                vehicleBuilder.setColor(color);
            }
            vehicleBuilder.setMileage(mileage);
            // Success always false unless this line returns true.
            success = this.removeVehicle(vehicleBuilder.build());
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to generate vehicle to remove.\n" + e);
        }
        return success;
    }

    /**
     * Remove a vehicle form the garage based on a Vehicle Object.
     *
     * @param vehicle - Vehicle to remove.
     *
     * @return true  - if vehicle successfully removed.
     *         false - if we failed to remove the vehicle from garage.
     */
    public boolean removeVehicle(Vehicle vehicle) {
        // If the object exists, will be true, else false.
        return garage.remove(vehicle);
    }

    /**
     * Replaces the vehicle in garage ArrayList at given index with the Vehicle
     * Object generated from parameters passed in.
     *
     * @param index Index of vehicle to replace.
     * @param make - The company who produced this car.
     * @param model - The name to identify the vehicle the company produced.
     * @param color Non null String to represent a color.
     * @param year - The year the vehicle was manufactured, must be after 1886.
     * @param mileage If invalid -1, else a non-negative value representing the vehicles' mileage.
     *
     * @return true  - if vehicle successfully updated.
     *         false - if we failed to update the vehicle in the garage.
     */
    public boolean updateVehicle(int index, String make, String model, String color, int year, int mileage) {
        boolean success = false;
        Vehicle.VehicleBuilder vehicle;

        try {
            vehicle = new Vehicle.VehicleBuilder(make, model, year);
            if (null != color && !color.isEmpty()) {
                vehicle.setColor(color);
            }
            vehicle.setMileage(mileage);
            // Success always false unless this line returns true.
            success = this.updateVehicle(index, vehicle.build());
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add vehicle.\n" + e);
        }

        return success;
    }

    /**
     * Replaces the vehicle in garage ArrayList at given index with the Vehicle
     * Object passed in.
     *
     * @param index Index of vehicle to replace.
     * @param vehicle Vehicle object to replace it with.
     *
     * @return true  - if vehicle successfully updated.
     *         false - if we failed to update the vehicle in the garage.

     */
    public boolean updateVehicle(int index, Vehicle vehicle) {
        boolean success = false;
        Vehicle oldVehicle;

        try {
            oldVehicle = garage.set(index, vehicle);
            // If we get to this point, and the element at this index was not
            // previously the same, then it was replaced.
            success = (!vehicle.equals(oldVehicle));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.\n" + e);
        }

        return success;
    }

    /**
     * Converts the arraylist of vehicles in garage into an array of all
     * vehicles in toString format.
     *
     * @return an array of all vehicles from garage in toString format.
     */
    public String[] listVehicleInfo() {
        String[] vehicles = new String[garage.size()];

        for (Vehicle vehicle : garage) {
            vehicles[garage.indexOf(vehicle)] = vehicle.toString();
        }

        return vehicles;
    }
}