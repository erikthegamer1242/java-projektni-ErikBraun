package braun.erik.prijevoz.model;


import braun.erik.prijevoz.components.DropdownConfig;
import braun.erik.prijevoz.components.HideConfig;
import braun.erik.prijevoz.model.exceptions.RouteCostNegativeException;
import braun.erik.prijevoz.model.subclasses.Driver;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Represents a whole Route with Driver, Stops and Vehicle
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
 *
 * @author erik
 * @version 1.0
 */
public final class Route implements Serializable, DisplayOption {

    /**
     * Route ID
     */
    @HideConfig(hide = true)
    private Integer id;

    /**
     * Route name
     */
    private String routeName;

    /**
     * Vehicle used on route
     */
    private Vehicle vehicle;

    /**
     * Driver driving the vehicle
     */
    private Driver driver;

    /**
     * Locations for the driver to stop at
     */
    @DropdownConfig(allowMultiple = true)
    private List<Stop> stops;

    /**
     * Cost of going to one stop
     */
    private BigDecimal stopCost;

    /**
     * Empty public constructor to allow XML and JSON deserialization
     */
    public Route() {}

    /**
     * Constructs a new route with these parameters:
     *
     * @param id        Unique Route ID
     * @param routeName Route name as a String
     * @param vehicle   A vehicle
     * @param driver    A driver
     * @param stops     A list of stops
     * @param stopCost  Cost for each stop (must be a positive decimal number)
     * @throws RouteCostNegativeException Route cost entered as a negative number
     * @throws NullPointerException       One or more parameters is null
     */
    public Route(Integer id, String routeName, Vehicle vehicle, Driver driver, List<Stop> stops, BigDecimal stopCost) throws RouteCostNegativeException, NullPointerException {
        this.id = Objects.requireNonNull(id, "Route ID cannot be null");
        this.vehicle = Objects.requireNonNull(vehicle, "Vehicle cannot be null");
        this.routeName = Objects.requireNonNull(routeName, "Route name cannot be null");
        this.driver = Objects.requireNonNull(driver, "Driver cannot be null");
        this.stopCost = Objects.requireNonNull(stopCost, "Stop cost cannot be null");
        if (this.stopCost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RouteCostNegativeException("Route cost cannot be negative. \n Entered route cost: " + this.stopCost);
        }
        this.stops = Objects.requireNonNull(stops, "Stops cannot be null");
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
     * @return List of stops
     */
    public List<Stop> getStops() {
        return stops;
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
     * @param stops List of stops
     * @throws NullPointerException if any Stop is null
     */
    public void setStops(List<Stop> stops) {
        this.stops = Objects.requireNonNull(stops, "Stops cannot be null");
    }
    /**
     * Append a new stop to the end of the list
     *
     * @param stop Stop object
     * @throws NullPointerException if Stop is null
     */
    public void addStop(Stop stop) {
        this.stops.add(Objects.requireNonNull(stop, "Stop cannot be null"));
    }
    /**
     * Set stop cost
     *
     * @param stopCost Decimal cost of one stop
     * @throws NullPointerException       if stop cost is null
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
     * Calculates the whole route cost based on the number of routes, and per stop cost
     *
     * @return BigDecimal result of stopCost * stops.size()
     */
    public BigDecimal getStopCost() {
        return this.stopCost.multiply(BigDecimal.valueOf(this.stops.size()));
    }
    /**
     * Overriding toString to give out a formatted route
     *
     * @return Returns a formatted string of the route
     */
    @Override
    public String toString() {
        return ("Route ID: " + this.id) + ("\tRoute Name: " + this.routeName) + ("\tDriver Name: " + this.driver.getName()) + ("\tVehicle Name: " + this.vehicle.getName()) + ("\tStop Count: " + this.stops.size()) + ("\tRoute Cost: " + this.getStopCost());
    }
    /**
     * Overriding equals to return proper matching for custom class.
     *
     * @param o the reference object with which to compare.
     * @return true if the object are equal false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Route route)) return false;
        return Objects.equals(id, route.id) && Objects.equals(routeName, route.routeName) && Objects.equals(vehicle, route.vehicle) && Objects.equals(driver, route.driver) && Objects.equals(stops, route.stops) && Objects.equals(stopCost, route.stopCost);
    }

    /**
     * Overriding hashCode to return proper hash for custom class.
     *
     * @return int hash of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, routeName, vehicle, driver, stops, stopCost);
    }

    @Override
    public String simpleName() {
        return routeName;
    }
}
