package entity;


public class Route {
    private Integer id;
    private String routeName;
    private Vehicle vehicle;
    private Driver driver;
    private Stop[] stops;
    private Integer stopLenght;
    private Integer stopCost;

    public Route(Integer id, String routeName, Vehicle vehicle, Driver driver, Stop[] stops, Integer stopLenght, Integer stopCost) {
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

    public Integer getStopLenght() {
        return stopCost;
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

    public void setStops(Stop[] stops,  Integer stopLenght) {
        for (int i = 0; i < stopLenght; i++) {
            this.stops[i] = stops[i];
        }
        this.stopLenght = stopLenght;
    }

    public void addStop(Stop stop) {
        this.stopLenght++;
        this.stops[this.stopLenght] = stop;
    }

    public void setStopCost(Integer stopCost) {
        this.stopCost = stopCost;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Integer getStopCost() {
        return this.stopCost * this.stopLenght;
    }

}
