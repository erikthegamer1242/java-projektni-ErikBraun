package braun.erik.prijevoz.model;

import braun.erik.prijevoz.components.HideConfig;
import braun.erik.prijevoz.model.exceptions.YearNegativeException;

import java.io.Serializable;
import java.util.Objects;

/**
 * Contains information about a vehicle
 * <p>
 * Implements Getters and Setters
 * <p>
 * Basic info
 * <ul>
 *     <li>Name</li>
 *     <li>Model</li>
 *     <li>Licence plate</li>
 *     <li>VIN Number</li>
 *     <li>Year of manufacturing</li>
 * </ul>
 *
 * @author erik
 * @version 1.0
 */

public final class Vehicle implements Serializable, DisplayOption {

    /**
     * Vehicle DB id
     */
    @HideConfig(hide = true)
    private Integer id;
    /**
     * Vehicle name
     */
    private String name;

    /**
     * Vehicle model
     */
    private String model;

    /**
     * Vehicle license plate number
     */
    private String licensePlate;

    /**
     * Vehicle VIN number
     */
    private String vin;

    /**
     * Vehicle production year
     */
    private Integer prodYear;

    /**
     * Vehicle ENUM motor type
     */
    private MotorType motorType;

    /**
     * Empty public constructor to allow XML and JSON deserialization
     */
    public Vehicle() {}

    @Override
    public String simpleName() {
        return name + " " + model + ": " + licensePlate;
    }

    /**
     * Used to set motor type for each vehicle.
     */
    public enum MotorType implements DisplayOption {
        /**
         * Diesel engine.
         */
        DIESEL("Diesel engine"),
        /**
         * Petrol engine.
         */
        PETROL("Petrol engine"),
        /**
         * LPG powered engine.
         */
        GAS("Gas engine"),
        /**
         * Fully electric motor.
         */
        ELECTRIC("Electric motor");

        private final String description;

        /**
         * Default constructor, sets description to the constant's name
         */
        MotorType() {
            this.description = this.name();
        }

        /**
         * Enum constructor allowing an addon of a description for each constant
         * @param description Description of each constant
         */
        MotorType(String description) {
            this.description = description;
        }

        /**
         * Return constant's description
         *
         * @return string description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Return constant's name
         *
         * @return string name
         */
        public String getName() {
            return this.name();
        }

        /**
         * Overriding toString to return our description instead of the constant name
         * @return String description
         */
        @Override
        public String toString() {
            return description;
        }

        @Override
        public String simpleName() {
            return description;
        }
    }


    /**
     * Constructs a new vehicle based on these parameters:
     *
     * @param id           Integer DB id
     * @param name         String vehicle name
     * @param model        String model type
     * @param licensePlate String licence plate number
     * @param vin          Integer VIN Number
     * @param prodYear         Integer year of manufacturing
     * @param motorType    the type of motor to set, see {@link MotorType}
     * @throws NullPointerException  when one or more arguments are null
     * @throws YearNegativeException if year is negative
     */
    public Vehicle(Integer id, String name, String model, String licensePlate, String vin, Integer prodYear, MotorType motorType) throws NullPointerException, YearNegativeException {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.model = Objects.requireNonNull(model, "model must not be null");
        this.licensePlate = Objects.requireNonNull(licensePlate, "licensePlate must not be null");
        this.vin = Objects.requireNonNull(vin, "vin must not be null");
        this.prodYear = Objects.requireNonNull(prodYear, "year must not be null");
        if (this.prodYear <= 0) {
            throw new YearNegativeException("Year cannot be negative. \n Entered year: " + this.prodYear);
        }
        this.motorType = Objects.requireNonNull(motorType, "motorType must not be null");
    }

    /**
     * Get vehicle DB id
     *
     * @return Integer DB id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get name for vehicle
     *
     * @return String vehicle name
     */
    public String getName() {
        return name;
    }

    /**
     * Get vehicle model
     *
     * @return String vehicle model
     */
    public String getModel() {
        return model;
    }

    /**
     * Get vehicle license plate
     *
     * @return String vehicle license plate
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Get vehicle VIN
     *
     * @return String vehicle vin
     */
    public String getVin() {
        return vin;
    }

    /**
     * Get vehicle year of manufacturing
     *
     * @return Integer vehicle year
     */
    public Integer getProdYear() {
        return prodYear;
    }

    /**
     * Get vehicle motor type
     * @return enum MotorType
     */
    public MotorType getMotorType() {return motorType;}

    /**
     * Set vehicle motor type
     * @param motorType enum MotorType
     */
    public void setMotorType(MotorType motorType) {this.motorType = motorType;}

    /**
     * Set vehicle name
     *
     * @param name vehicle name
     * @throws NullPointerException if name is null
     */
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    /**
     * Set model
     *
     * @param model model type
     * @throws NullPointerException if model is null
     */
    public void setModel(String model) {
        this.model = Objects.requireNonNull(model, "model must not be null");
    }

    /**
     * Set license plate
     *
     * @param licensePlate license plate number
     * @throws NullPointerException if licensePlate is null
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = Objects.requireNonNull(licensePlate, "licensePlate must not be null");
    }

    /**
     * Set VIN
     *
     * @param vin VIN number
     * @throws NullPointerException if vin is null
     */
    public void setVin(String vin) {
        this.vin = Objects.requireNonNull(vin, "vin must not be null");
    }

    /**
     * Set year of manufacturing
     *
     * @param prodYear year
     * @param <T> Type of year
     * @throws NullPointerException  if year is null
     * @throws YearNegativeException if year is negative
     */
    public <T extends Number> void setProdYear(T prodYear) throws YearNegativeException {
        this.prodYear = Objects.requireNonNull(prodYear, "year must not be null").intValue();
        if (this.prodYear <= 0) {
            throw new YearNegativeException("Year cannot be negative. \n Entered year: " + this.prodYear);
        }
    }

    /**
     * Overriding toString to give out a formatted vehicle
     *
     * @return Returns a formatted string of the vehicle
     */
    @Override
    public String toString() {
        return ("Vehicle Name: " + this.name)
                + ("\tVehicle Model: " + this.model)
                + ("\tVehicle LicensePlate: " + this.licensePlate)
                + ("\tVehicle Vin: " + this.vin)
                + ("\tVehicle Year: " + this.prodYear)
                + ("\tVehicle MotorType: " + this.motorType.toString());
    }

    /**
     * Overriding equals to return proper matching for custom class.
     *
     * @param o the reference object with which to compare.
     * @return true if the object are equal false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(name, vehicle.name) && Objects.equals(model, vehicle.model) && Objects.equals(licensePlate, vehicle.licensePlate) && Objects.equals(vin, vehicle.vin) && Objects.equals(prodYear, vehicle.prodYear) && motorType == vehicle.motorType;
    }

    /**
     * Overriding hashCode to return proper hash for custom class.
     *
     * @return int hash of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, model, licensePlate, vin, prodYear, motorType);
    }
}
