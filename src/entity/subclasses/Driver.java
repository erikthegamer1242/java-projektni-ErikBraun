package entity.subclasses;

import entity.superclasses.Person;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class Driver extends Person implements Employee {
    private final String licenseNumber;
    private final BigDecimal salary;

    private Driver(DriverBuilder builder) {
        this.oib = builder.oib;
        this.surname = builder.surname;
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.dateOfBirth = builder.dateOfBirth;
        this.licenseNumber = builder.licenseNumber;
        this.salary = builder.salary;
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
                "\tDriver DateOfBirth: " + this.dateOfBirth +
                "\tDriver Salary: " + calculatePay(salary, BigDecimal.valueOf(160));
    }

    public static class DriverBuilder {
        private final String oib;
        private final String name;
        private final String surname;
        private final String licenseNumber;
        private final BigDecimal salary;

        private String email = "";
        private String phoneNumber = "";
        private LocalDate dateOfBirth = LocalDate.EPOCH;

        public DriverBuilder(String oib, String name, String surname, String licenseNumber, BigDecimal salary) {
            this.oib = oib;
            this.name = name;
            this.surname = surname;
            this.licenseNumber = licenseNumber;
            this.salary = salary;
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
