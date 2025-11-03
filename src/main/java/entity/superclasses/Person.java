package entity.superclasses;

import java.time.LocalDate;

/**
 * Contains basic info about a person
 *
 * Implements Getters
 *
 * Basic info
 * <ul>
 *     <li>Name</li>
 *     <li>Surname</li>
 *     <li>OIB</li>
 *     <li>Email</li>
 *     <li>Phone number</li>
 *     <li>Date of birth</li>
 * </ul>
 * @author erik
 * @version 1.0
 */
public abstract class Person {
    protected String name;
    protected String surname;
    protected String oib;
    protected String email;
    protected String phoneNumber;
    protected LocalDate dateOfBirth;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getOib() {
        return oib;
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

    /**
     * Overriding toString to properly output data
     * @return String formatted for each class
     */
    @Override
    public abstract String toString();
}
