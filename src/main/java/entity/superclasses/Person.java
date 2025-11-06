package entity.superclasses;

import entity.Vehicle;
import entity.subclasses.Driver;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Contains basic info about a person
 * <p>
 * Implements Getters
 * <p>
 * Basic info
 * <ul>
 *     <li>Name</li>
 *     <li>Surname</li>
 *     <li>OIB</li>
 *     <li>Email</li>
 *     <li>Phone number</li>
 *     <li>Date of birth</li>
 * </ul>
 *
 * @author erik
 * @version 1.0
 */
public abstract class Person {
    /**
     * A person's name
     */
    protected String name;
    /**
     * A person's surname
     */
    protected String surname;
    /**
     * A person's personal identification number
     */
    protected String oib;
    /**
     * A person's email
     */
    protected String email;
    /**
     * A person's phone number
     */
    protected String phoneNumber;
    /**
     * A person's date of birth
     */
    protected LocalDate dateOfBirth;

    /**
     * Gets a person's name
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a person's surname
     *
     * @return String surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Gets a person's oib
     *
     * @return String oib
     */
    public String getOib() {
        return oib;
    }

    /**
     * Gets a person's email
     *
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets a person's phone number
     *
     * @return String phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets a person's date of birth
     *
     * @return LocalDate date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Overriding toString to properly output data
     *
     * @return String formatted for each class
     */
    @Override
    public abstract String toString();

    /**
     * Overriding equals to return proper matching for custom class.
     *
     * @param o the reference object with which to compare.
     * @return true if the object are equal false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(oib, person.oib) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }

    /**
     * Overriding hashCode to return proper hash for custom class.
     *
     * @return int hash of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(oib, name, surname);
    }
}
