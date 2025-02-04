package com.await.dddcore.valueobjects;

import com.await.dddcore.exceptions.InvalidNameException;
import java.util.Objects;

/**
 * Represents a person's full name with a first and last name.
 * Ensures that names are properly validated before being used.
 */
public final class Name {

    private final String firstName;  // The first name of the person
    private final String lastName;   // The last name of the person

    /**
     * Constructs a Name object with a given first and last name.
     * Throws an exception if either the first name or last name is null or empty.
     *
     * @param firstName The person's first name
     * @param lastName The person's last name
     */
    public Name(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidNameException("First name cannot be empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidNameException("Last name cannot be empty");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    /**
     * Returns the first name.
     *
     * @return The person's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name.
     *
     * @return The person's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the full name by combining the first and last names.
     *
     * @return The person's full name (first + last)
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Compares two Name objects for equality.
     * Two Name objects are considered equal if their first and last names are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name name)) return false;
        return firstName.equals(name.firstName) && lastName.equals(name.lastName);
    }

    /**
     * Generates a hash code for the Name object based on its first and last name.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    /**
     * Returns a string representation of the Name object.
     * The string is the full name (first + last).
     */
    @Override
    public String toString() {
        return getFullName();
    }
}
