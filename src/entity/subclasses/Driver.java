package entity.subclasses;

import entity.superclasses.Person;

import java.time.LocalDate;

public class Driver extends Person {
    private final String licenseNumber;

    private Driver(DriverBuilder builder) {
        this.oib = builder.oib;
        this.surname = builder.surname;
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.dateOfBirth = builder.dateOfBirth;
        this.licenseNumber = builder.licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public String toString() {
        return "Driver Name: " + this.name +
                "\tDriver Surname: " + this.surname +
                "\tDriver LicenseNumber: " + this.licenseNumber +
                "\tDriver Email: " + this.email +
                "\tDriver DateOfBirth: " + this.dateOfBirth;
    }

    public static class DriverBuilder {
        private final String oib;
        private final String name;
        private final String surname;
        private final String licenseNumber;

        private String email = "";
        private String phoneNumber = "";
        private LocalDate dateOfBirth = LocalDate.EPOCH;

        public DriverBuilder(String oib, String name, String surname, String licenseNumber) {
            this.oib = oib;
            this.name = name;
            this.surname = surname;
            this.licenseNumber = licenseNumber;
        }

        public DriverBuilder email(String email) {
            this.email = email;
            return this;
        }

        public DriverBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public DriverBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Driver build() {
            return new Driver(this);
        }
    }
}
