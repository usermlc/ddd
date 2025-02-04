package com.await.dddcore.valueobjects;

import com.await.dddcore.exceptions.InvalidEmailException;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents an immutable Email value object.
 * Ensures that the provided email follows a valid format.
 */
public final class Email {

    // Regular expression pattern for validating email format
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    private final String email;

    /**
     * Constructs an Email object after validation.
     *
     * @param email The email string to validate and store.
     * @throws InvalidEmailException if the email format is invalid.
     */
    public Email(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Invalid email format");
        }
        this.email = email.trim(); // Trim spaces to maintain consistency
    }

    /**
     * Returns the stored email as a string.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Checks equality based on the email string value.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email1)) return false;
        return email.equals(email1.email);
    }

    /**
     * Generates a hash code based on the email value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * Returns the email as a string representation.
     */
    @Override
    public String toString() {
        return email;
    }
}
