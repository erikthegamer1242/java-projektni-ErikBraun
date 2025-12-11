package braun.erik.prijevoz.model.subclasses;

import braun.erik.prijevoz.model.superclasses.Person;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Driver class containing extend data related to driver's like licenseNumber, salary
 *
 * @author erik
 * @version 1.0
 */

public final class Driver extends Person implements Employed, Serializable {
    private String licenseNumber;
    private BigDecimal salary;
    private BigDecimal workingHours;

    /**
     * Empty public constructor to allow XML and JSON deserialization
     */
    public Driver() {}

    /**
     * Constructs a new driver based on data from the builder pattern
     *
     * @param builder builder pattern containing parameters defined bellow
     */
    private Driver(DriverBuilder builder) {
        this.oib = builder.oib;
        this.surname = builder.surname;
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.dateOfBirth = builder.dateOfBirth;
        this.licenseNumber = builder.licenseNumber;
        this.salary = builder.salary;
        this.workingHours = builder.workingHours;
    }

    /**
     * Get driver's licence number
     *
     * @return String driver's licence number
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Sets driver's licence number
     *
     * @param licenseNumber String driver's licence number
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * Get driver's hourly salary rate
     *
     * @return BigDecimal driver's salary rate
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Sets driver's hourly salary rate
     *
     * @param salary BigDecimal driver's salary rate
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Get driver's working hours
     *
     * @return BigDecimal driver's working hours
     */
    public BigDecimal getWorkingHours() {
        return workingHours;
    }

    /**
     * Sets driver's working hours
     *
     * @param workingHours BigDecimal driver's working hours
     */
    public void setWorkingHours(BigDecimal workingHours) {
        this.workingHours = workingHours;
    }

    /**
     * Overriding toString to give out a formatted driver
     *
     * @return Returns a formatted string of the driver
     */
    @Override
    public String toString() {
        return "Driver Name: " + this.name + "\tDriver Surname: " + this.surname + "\tDriver LicenseNumber: " + this.licenseNumber + "\tDriver Email: " + this.email + "\tDriver DateOfBirth: " + this.dateOfBirth + "\tDriver Salary: " + calculatePay();
    }

    @Override
    public BigDecimal calculatePay() {
        return salary.multiply(workingHours);
    }

    /**
     * Driver relies on Person.equals()/hashCode() which compare oib, name and surname.
     * Redundant overrides were removed to avoid duplicate/identical implementations.
     */

    /**
     * Subclass implementing a builder pattern to ease the creation of a driver object when having optional parameter
     * <p>
     * Required fields:
     * <ul>
     *     <li>OIB</li>
     *     <li>Name</li>
     *     <li>Surname</li>
     *     <li>Driver licence number</li>
     *     <li>Salary</li>
     *     <li>Working Hours</li>
     * </ul>
     * <p>
     * Optional fields:
     * <ul>
     *     <li>Email</li>
     *     <li>Phone number</li>
     *     <li>Date of birth (set to unix EPOCH)</li>
     * </ul>
     */
    public static class DriverBuilder {
        private final String oib;
        private final String name;
        private final String surname;
        private final String licenseNumber;
        private final BigDecimal salary;
        private final BigDecimal workingHours;

        private String email = "";
        private String phoneNumber = "";
        private LocalDate dateOfBirth = LocalDate.EPOCH;

        /**
         * Constructs a new builder pattern object with only the required parameter
         *
         * @param oib           String driver's OIB
         * @param name          String driver's first name
         * @param surname       String driver's last name
         * @param licenseNumber String driver's licence number
         * @param salary        Decimal driver's hourly pay
         * @param workingHours  Decimal driver's working hours
         * @throws NullPointerException when one or more parameter are null
         */
        public DriverBuilder(String oib, String name, String surname, String licenseNumber, BigDecimal salary, BigDecimal workingHours) {
            this.oib = Objects.requireNonNull(oib, "oib must not be null");
            this.name = Objects.requireNonNull(name, "name must not be null");
            this.surname = Objects.requireNonNull(surname, "surname must not be null");
            this.licenseNumber = Objects.requireNonNull(licenseNumber, "licenseNumber must not be null");
            this.salary = Objects.requireNonNull(salary, "salary must not be null");
            this.workingHours = Objects.requireNonNull(workingHours, "workingHours must not be null");
        }

        /**
         * Set optional parameter email.
         *
         * @param email String driver's email
         * @return this builder
         * @throws NullPointerException if email is null
         */
        public DriverBuilder email(String email) {

            this.email = Objects.requireNonNull(email, "email must not be null");
            return this;
        }

        /**
         * Set optional parameter phoneNumber.
         *
         * @param phoneNumber String driver's phone number
         * @return this builder
         * @throws NullPointerException if phoneNumber is null
         */
        public DriverBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = Objects.requireNonNull(phoneNumber, "phoneNumber must not be null");
            return this;
        }

        /**
         * Set optional parameter dateOfBirth.
         *
         * @param dateOfBirth LocalDate driver's date of birth
         * @return this builder
         * @throws NullPointerException if dateOfBirth is null
         */
        public DriverBuilder dateOfBirth(LocalDate dateOfBirth) {
            Objects.requireNonNull(dateOfBirth, "dateOfBirth must not be null");
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        /**
         * Calls the constructor of the driver class
         *
         * @return returns new object of driver, with all parameters set up
         */
        public Driver build() {
            return new Driver(this);
        }
    }
}
