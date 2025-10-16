package entity;

import java.time.LocalDate;

public class Driver {
    private String name;
    private String surname;
    private String licenseNumber;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public Driver(String name, String surname, String licenseNumber, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.licenseNumber = licenseNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void printDriver() {
        System.out.print("Driver Name: " + this.name);
        System.out.print("\tDriver Surname: " + this.surname);
        System.out.print("\tDriver LicenseNumber: " + this.licenseNumber);
        System.out.print("\tDriver Email: " + this.email);
        System.out.println("\tDriver Date Of Birth: " + this.dateOfBirth.toString());
    }
}
