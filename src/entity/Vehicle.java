package entity;

public class Vehicle {
    private String name;
    private String model;
    private String licensePlate;
    private String vin;
    private int year;

    public Vehicle(String name, String model, String licensePlate, String vin, int year) {
        this.name = name;
        this.model = model;
        this.licensePlate = licensePlate;
        this.vin = vin;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
