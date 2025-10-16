package entity;

public class Vehicle {
    private String name;
    private String model;
    private String licensePlate;
    private String vin;
    private Integer year;

    public Vehicle(String name, String model, String licensePlate, String vin, Integer year) {
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

    public Integer getYear() {
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

    public void setYear(Integer year) {
        this.year = year;
    }

    public void printVehicle() {
        System.out.print("Vehicle Name: " + this.name);
        System.out.print("\tVehicle Model: " + this.model);
        System.out.print("\tVehicle LicensePlate: " + this.licensePlate);
        System.out.print("\tVehicle Vin: " + this.vin);
        System.out.println("\tVehicle Year: " + this.year);


    }

}
