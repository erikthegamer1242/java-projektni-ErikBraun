package entity;

import java.util.List;

public class Relation {
    private int id;
    private Vehicle vehicle;
    private Driver driver;
    private List<Stop> stops;

    public Relation(int id, Vehicle vehicle, Driver driver, List<Stop> stops) {
        this.id = id;
        this.vehicle = vehicle;
        this.driver = driver;
        this.stops = stops;
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public void addStop(Stop stop) {
        this.stops.add(stop);
    }

    public void removeStop(Stop stop) {
        this.stops.remove(stop);
    }
}
