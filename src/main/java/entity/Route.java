package entity;


import entity.exceptions.RouteCostNegativeException;
import entity.subclasses.Driver;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a whole Route with Driver, Stops and Vehicle
 * <p>
 * <p>
 * Basic info
 * <ul>
 *     <li>ID</li>
 *     <li>Route name</li>
 *     <li>Vehicle</li>
 *     <li>Driver</li>
 *     <li>Stops</li>
 *     <li>Each stop cost</li>
 * </ul>
 * @author erik
 * @version 1.0
 */
public class Route {
    private Integer id;
    private String routeName;
    private Vehicle vehicle;
    private Driver driver;
    private Stop[] stops;
    private Integer stopLength;
    private BigDecimal stopCost;


    /**
     * Constructs a new route with these parameters:
     *
     * @param id         Unique Route ID
     * @param routeName  Route name as a String
     * @param vehicle    A vehicle
     * @param driver     A driver
     * @param stops      An array of stops
     * @param stopLength Length of stops array
     * @param stopCost   Cost for each stop (must be a positive decimal number)
     * @throws RouteCostNegativeException Route cost entered as a negative number
     * @throws NullPointerException       One or more parameters is null
     */
    public Route(Integer id, String routeName, Vehicle vehicle, Driver driver, Stop[] stops, Integer stopLength, BigDecimal stopCost) throws RouteCostNegativeException, NullPointerException {
        this.id = Objects.requireNonNull(id, "Route ID cannot be null");
        this.vehicle = Objects.requireNonNull(vehicle, "Vehicle cannot be null");
        this.routeName = Objects.requireNonNull(routeName, "Route name cannot be null");
        this.driver = Objects.requireNonNull(driver, "Driver cannot be null");
        this.stopCost = Objects.requireNonNull(stopCost, "Stop cost cannot be null");
        if (this.stopCost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RouteCostNegativeException("Route cost cannot be negative. \n Entered route cost: " + this.stopCost);
        }
        this.stopLength = Objects.requireNonNull(stopLength, "Stop length cannot be null");
        this.stops = Arrays.copyOf(Objects.requireNonNull(stops, "Stops cannot be null"), stopLength);
    }

    /**
     * Get stop ID
     *
     * @return Integer stop ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get route vehicle
     *
     * @return An Vehicle object
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Get route driver
     *
     * @return A Driver object
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Get route stops
     *
     * @return Array of stops
     */
    public Stop[] getStops() {
        return stops;
    }

    /**
     * Get route stops array length
     *
     * @return Integer stops array length
     */
    public Integer getStopLength() {
        return stopLength;
    }

    /**
     * Get route name
     *
     * @return String route name
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     * Set ID for route
     *
     * @param id Integer route ID
     * @throws NullPointerException if Route ID is null
     */
    public void setId(Integer id) {
        this.id = Objects.requireNonNull(id, "Route ID cannot be null");
    }

    /**
     * Set Vehicle for route
     *
     * @param vehicle Vehicle object used in route
     * @throws NullPointerException if Vehicle is null
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = Objects.requireNonNull(vehicle, "Vehicle cannot be null");
    }

    /**
     * Set Driver for route
     *
     * @param driver Driver object used in route
     * @throws NullPointerException if Driver is null
     */
    public void setDriver(Driver driver) {
        this.driver = Objects.requireNonNull(driver, "Driver cannot be null");
    }

    /**
     * Set stops for route
     *
     * @param stops      Array of stops
     * @param stopLength Stop length
     * @throws NullPointerException if any Stop is null or stopLength is null
     */
    public void setStops(Stop[] stops, Integer stopLength) {
        for (int i = 0; i < stopLength; i++) {
            this.stops[i] = Objects.requireNonNull(stops[i], "Stop cannot be null");
        }
        this.stopLength = Objects.requireNonNull(stopLength, "Stop length cannot be null");
    }

    /**
     * Append a new stop to the end of the array
     *
     * @param stop Stop object
     * @throws NullPointerException if Stop is null
     */
    public void addStop(Stop stop) {
        this.stopLength++;
        this.stops[this.stopLength] = Objects.requireNonNull(stop, "Stop cannot be null");
    }

    /**
     * Set stop cost
     *
     * @param stopCost Decimal cost of one stop
     * @throws NullPointerException if stop cost is null
     * @throws RouteCostNegativeException if stop cost is negative
     */
    public void setStopCost(BigDecimal stopCost) throws RouteCostNegativeException {
        this.stopCost = Objects.requireNonNull(stopCost, "Stop cost cannot be null");
        if (this.stopCost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RouteCostNegativeException("Route cost cannot be negative. \n Entered route cost: " + this.stopCost);
        }
    }

    /**
     * Set route name
     * 
     * @param routeName String containing a route name
     * @throws NullPointerException if string is null
     */
    public void setRouteName(String routeName) {
        this.routeName = Objects.requireNonNull(routeName, "Route name cannot be null");
    }

    /**
     * Calculates the whole route cost based on the number of routes, and route cost
     *
     * @return BigDecimal result of stopCost * stopLength
     */
    public BigDecimal getStopCost() {
        return this.stopCost.multiply(BigDecimal.valueOf(this.stopLength));
    }

    /**
     * Overriding toString to give out a formatted route
     * @return Returns a formatted string of the route
     */
    @Override
    public String toString() {
        return ("Route ID: " + this.id)
        + ("\tRoute Name: " + this.routeName)
        + ("\tDriver Name: " + this.driver.getName())
        + ("\tVehicle Name: " + this.vehicle.getName())
        + ("\tStop Count: " + this.stopLength)
        + ("\tRoute Cost: " + this.getStopCost());
    }

}
