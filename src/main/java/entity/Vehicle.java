package entity;

import java.math.BigDecimal;
import java.util.Objects;

import entity.exceptions.YearNegativeException;

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
 * @author erik
 * @version 1.0
 */
public class Vehicle {
    private String name;
    private String model;
    private String licensePlate;
    private String vin;
    private Integer year;

    /**
     * Constructs a new vehicle based on these parameters:
     *
     * @param name         String vehicle name
     * @param model        String model type
     * @param licensePlate String licence plate number
     * @param vin          Integer VIN Number
     * @param year         Integer year of manufacturing
     * @throws NullPointerException  when one or more arguments are null
     * @throws YearNegativeException if year is negative
     */
    public Vehicle(String name, String model, String licensePlate, String vin, Integer year) throws YearNegativeException {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.model = Objects.requireNonNull(model, "model must not be null");
        this.licensePlate = Objects.requireNonNull(licensePlate, "licensePlate must not be null");
        this.vin = Objects.requireNonNull(vin, "vin must not be null");
        this.year = Objects.requireNonNull(year, "year must not be null");
        if (this.year <= 0) {
            throw new YearNegativeException("Year cannot be negative. \n Entered year: " + this.year);
        }
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
    public Integer getYear() {
        return year;
    }

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
     * @param year year
     * @throws NullPointerException  if year is null
     * @throws YearNegativeException if year is negative
     */
    public void setYear(Integer year) throws YearNegativeException {
        this.year = Objects.requireNonNull(year, "year must not be null");
        if (this.year <= 0) {
            throw new YearNegativeException("Year cannot be negative. \n Entered year: " + this.year);
        }
    }

    /**
     * Overriding toString to give out a formatted vehicle
     * @return Returns a formatted string of the vehicle
     */
    @Override
    public String toString() {
        return ("Vehicle Name: " + this.name)
        + ("\tVehicle Model: " + this.model)
        + ("\tVehicle LicensePlate: " + this.licensePlate)
        + ("\tVehicle Vin: " + this.vin)
        + ("\tVehicle Year: " + this.year);
    }

}
