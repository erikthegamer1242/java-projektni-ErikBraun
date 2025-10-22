package entity;


import entity.subclasses.Driver;

import java.math.BigDecimal;

public class Route {
    private Integer id;
    private String routeName;
    private Vehicle vehicle;
    private Driver driver;
    private Stop[] stops;
    private Integer stopLenght;
    private BigDecimal stopCost;

    public Route(Integer id, String routeName, Vehicle vehicle, Driver driver, Stop[] stops, Integer stopLenght, BigDecimal stopCost) {
        this.id = id;
        this.vehicle = vehicle;
        this.routeName = routeName;
        this.driver = driver;
        this.stopCost =  stopCost;
        this.stopLenght = stopLenght;
        this.stops = new Stop[stopLenght];
        for (int i = 0; i < stopLenght; i++) {
            this.stops[i] = stops[i];
        }
    }

    public Integer getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public Stop[] getStops() {
        return stops;
    }

    public Integer getStopLength() {
        return stopLenght;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setStops(Stop[] stops,  Integer stopLength) {
        for (int i = 0; i < stopLength; i++) {
            this.stops[i] = stops[i];
        }
        this.stopLenght = stopLength;
    }

    public void addStop(Stop stop) {
        this.stopLenght++;
        this.stops[this.stopLenght] = stop;
    }

    public void setStopCost(BigDecimal stopCost) {
        this.stopCost = stopCost;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public BigDecimal getStopCost() {
        return this.stopCost.multiply(BigDecimal.valueOf(this.stopLenght));
    }

    public void printRoute() {
        System.out.print("Route ID: " + this.id);
        System.out.print("\tRoute Name: " + this.routeName);
        System.out.print("\tDriver Name: " + this.driver.getName());
        System.out.print("\tVehicle Name: " + this.vehicle.getName());
        System.out.print("\tStop Count: " + this.stopLenght);
        System.out.println("\tRoute Cost: " + this.getStopCost());
    }

}
