package entity.superclasses;

import java.time.LocalDate;

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

    public abstract String toString();
    }
}
