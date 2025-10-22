package entity.subclasses;

import entity.superclasses.Person;

import java.time.LocalDate;
import java.util.UUID;

public class User extends Person {
    private final UUID subscriberID;

    private User(UserBuilder builder) {
        this.oib = builder.oib;
        this.surname = builder.surname;
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.dateOfBirth = builder.dateOfBirth;
        this.subscriberID = builder.subscriberID;
    }

    public UUID getSubscriberID() {
        return subscriberID;
    }

    @Override
    public String toString() {
        return "User Name: " + this.name +
                "\tUser Surname: " + this.surname +
                "\tUser Card/SubscriberID: " + this.subscriberID +
                "\tUser Email: " + this.email +
                "\tUser DateOfBirth: " + this.dateOfBirth;
    }

    public static class UserBuilder {
        private final String oib;
        private final String name;
        private final String surname;

        private UUID subscriberID = UUID.randomUUID();
        private String email = "";
        private String phoneNumber = "";
        private LocalDate dateOfBirth = LocalDate.EPOCH;

        public UserBuilder(String oib, String name, String surname) {
            this.oib = oib;
            this.name = name;
            this.surname = surname;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder subscriberID(UUID subscriberID) {
            this.subscriberID = subscriberID;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
