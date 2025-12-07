package entity.subclasses;

import entity.superclasses.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Administrator class containing extended data related to administrators
 *
 * @author erik
 * @version 1.0
 */
public final class Administrator extends Person implements Employed, AdministratorActions {
    private BigDecimal salary;
    private BigDecimal workingHours;

    /**
     * Empty public constructor to allow XML and JSON deserialization
     */
    public Administrator() {}

    /**
     * Constructs a new administrator based on data from the builder pattern
     *
     * @param builder builder pattern containing parameters defined bellow
     */
    private Administrator(Administrator.AdministratorBuilder builder) {
        this.oib = builder.oib;
        this.surname = builder.surname;
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.dateOfBirth = builder.dateOfBirth;
        this.salary = builder.salary;
        this.workingHours = builder.workingHours;
    }

    /**
     * Overriding toString to give out a formatted Administrator
     *
     * @return Returns a formatted string of the Administrator
     */
    @Override
    public String toString() {
        return "Administrator Name: " + this.name + "\tAdministrator Surname: " + this.surname + "\tAdministrator Email: " + this.email + "\tAdministrator DateOfBirth: " + this.dateOfBirth + "\tAdministrator Salary: " + calculatePay();
    }

    @Override
    public BigDecimal calculatePay() {
        return salary.multiply(workingHours);
    }

    /**
     * Overriding equals to return proper matching for custom class.
     *
     * @param o the reference object with which to compare.
     * @return true if the object are equal false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Administrator administrator)) return false;
        return Objects.equals(oib, administrator.oib) && Objects.equals(name, administrator.name) && Objects.equals(surname, administrator.surname);
    }

    /**
     * Subclass implementing a builder pattern to ease the creation of an Administrator object when having optional parameter
     * <p>
     * Required fields:
     * <ul>
     *     <li>OIB</li>
     *     <li>Name</li>
     *     <li>Surname</li>
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
    public static class AdministratorBuilder {
        private final String oib;
        private final String name;
        private final String surname;
        private final BigDecimal salary;
        private final BigDecimal workingHours;

        private String email = "";
        private String phoneNumber = "";
        private LocalDate dateOfBirth = LocalDate.EPOCH;

        /**
         * Constructs a new builder pattern object with only the required parameter
         *
         * @param oib           String Administrator's OIB
         * @param name          String Administrator's first name
         * @param surname       String Administrator's last name
         * @param salary        Decimal Administrator's hourly pay
         * @param workingHours  Decimal Administrator's working hours
         * @throws NullPointerException when one or more parameter are null
         */
        public AdministratorBuilder(String oib, String name, String surname, BigDecimal salary, BigDecimal workingHours) {
            this.oib = Objects.requireNonNull(oib, "oib must not be null");
            this.name = Objects.requireNonNull(name, "name must not be null");
            this.surname = Objects.requireNonNull(surname, "surname must not be null");
            this.salary = Objects.requireNonNull(salary, "salary must not be null");
            this.workingHours = Objects.requireNonNull(workingHours, "workingHours must not be null");
        }

        /**
         * Set optional parameter email.
         *
         * @param email String Administrator's email
         * @return this builder
         * @throws NullPointerException if email is null
         */
        public Administrator.AdministratorBuilder email(String email) {

            this.email = Objects.requireNonNull(email, "email must not be null");
            return this;
        }

        /**
         * Set optional parameter phoneNumber.
         *
         * @param phoneNumber String Administrator's phone number
         * @return this builder
         * @throws NullPointerException if phoneNumber is null
         */
        public Administrator.AdministratorBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = Objects.requireNonNull(phoneNumber, "phoneNumber must not be null");
            return this;
        }

        /**
         * Set optional parameter dateOfBirth.
         *
         * @param dateOfBirth LocalDate Administrator's date of birth
         * @return this builder
         * @throws NullPointerException if dateOfBirth is null
         */
        public Administrator.AdministratorBuilder dateOfBirth(LocalDate dateOfBirth) {
            Objects.requireNonNull(dateOfBirth, "dateOfBirth must not be null");
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        /**
         * Calls the constructor of the Administrator class
         *
         * @return returns new object of Administrator, with all parameters set up
         */
        public Administrator build() {
            return new Administrator(this);
        }
    }
}
