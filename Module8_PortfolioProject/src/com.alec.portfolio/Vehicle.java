package com.alec.portfolio;

/**
 * Class to represent a vehicle in inventory, use provided builder class to initialize.
 */
public class Vehicle {
    public static final int INVALID_INT_VALUE = -1;
    private final String make;
    private final String model;
    private final String color;
    private final int year;
    private final int mileage;

    /**
     * Default constructor, made private to avoid use outside of this class.
     */
    private Vehicle() {
        this("", "", "", INVALID_INT_VALUE, INVALID_INT_VALUE);
    }

    /**
     * Parameterized constructor, used by builder class. Made private to avoid use outside of this class.
     *
     * @param make - The company who produced this car.
     * @param model - The name to identify the vehicle the company produced.
     * @param color Non null String to represent a color.
     * @param year - The year the vehicle was manufactured, must be after 1886.
     * @param mileage If invalid -1, else a non-negative value representing the vehicles' mileage.
     */
    private Vehicle(String make, String model, String color, int year, int mileage) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
    }

    /**
     * Version to accept builder type. Made private to avoid use outside of this class.
     * @param vehicleBuilder The instance of the builder class to init this object.
     */
    private Vehicle(VehicleBuilder vehicleBuilder) {
        this(vehicleBuilder.MAKE, vehicleBuilder.MODEL, vehicleBuilder.color, vehicleBuilder.YEAR, vehicleBuilder.mileage);
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "make: " + this.getMake() + ", model: " + this.getModel() + ", color: "
                + ((this.getColor().isEmpty()) ? "N/A" : this.getColor())
                + ", year: " + this.getYear() + ", mileage: "
                + (((INVALID_INT_VALUE == this.getMileage()) ? "N/A" : this.getMileage()));
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        Vehicle car;

        try {
            // Check if object is an instance of Vehicle.
            if (obj instanceof Vehicle) {
                car = (Vehicle) obj;
                // If all values are equivalent, then is equal.
                isEqual = (this.getMake().equals(car.getMake())
                        && this.getModel().equals(car.getModel())
                        && this.getColor().equals(car.getColor())
                        && this.getYear() == car.getYear()
                        && this.getMileage() == car.getMileage());
            }
        } catch (ClassCastException e) {
            System.out.println("Failed to cast, not equal.");
        }

        return isEqual;
    }

    /**
     * I decided to use a builder pattern to init my car Object, and make all constructors private
     * to the Vehicle class to prevent invoking those outside of this builder class.
     */
    public static class VehicleBuilder {
        /* Constants. */
        private static final int YEAR_OF_FIRST_CAR = 1886;

        /* Required values, only aloud to set once since these should never change. */
        private final String MAKE;
        private final String MODEL;
        private final int YEAR;

        /* Optional values. */
        private String color;
        private int mileage;

        /**
         * Minimally required attributes.
         *
         * @param make - The company who produced this car.
         * @param model - The name to identify the vehicle the company produced.
         * @param year - The year the vehicle was manufactured, must be after 1886.
         *
         * @throws IllegalArgumentException - In the event an illegal value was passed in.
         */
        public VehicleBuilder(String make, String model, int year) throws IllegalArgumentException {
            if (null == make || null == model) {
                throw new IllegalArgumentException("Null values not allowed in constructor.");
            } else if (make.isEmpty() || model.isEmpty()) {
                throw new IllegalArgumentException("empty values not allowed in constructor.");
            }else if (year < YEAR_OF_FIRST_CAR) {
                throw new IllegalArgumentException("Cars did not exist before: " + YEAR_OF_FIRST_CAR);
            }

            // Set Required values from parameters.
            this.MAKE = make;
            this.MODEL = model;
            this.YEAR = year;

            // initialize all non-required values.
            this.color = "";
            this.mileage = INVALID_INT_VALUE;
        }

        /**
         * Sets the color of the car.
         *
         * @param color Non null String to represent a color.
         *
         * @return This object in current state to comply to builder pattern.
         * @throws IllegalArgumentException Value of color was null.
         */
        public VehicleBuilder setColor(String color) throws IllegalArgumentException {
            if (null == color) {
                throw new IllegalArgumentException("Null values not allowed for color.");
            }
            this.color = color;
            return this;
        }

        /**
         *
         * @param mileage If invalid -1, else a non-negative value representing the vehicles' mileage.
         *
         * @return This object in current state to comply to builder pattern.
         * @throws IllegalArgumentException Value of mileage was not -1 or non-negative value.
         */
        public VehicleBuilder setMileage(int mileage) throws IllegalArgumentException {
            if (mileage < 0 && mileage != INVALID_INT_VALUE) {
                throw new IllegalArgumentException("Mileage cannot be less than 0.");
            }
            this.mileage = mileage;
            return this;
        }

        /**
         * Generates a Vehicle object from this builder object.
         *
         * @return Vehicle with values form this class.
         */
        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}