package com.await.dddcore.valueobjects;

import com.await.dddcore.exceptions.InvalidAddressException;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents an immutable Address value object.
 * Ensures validity by enforcing non-null and correctly formatted fields.
 */
public final class Address {

    private final String country; // Country of the address
    private final String city; // City of the address
    private final String street; // Street name
    private final String postalCode; // Postal code in a valid format

    private static final Pattern POSTAL_CODE_PATTERN = Pattern.compile("\\d{5}"); // 5-digit postal code validation

    /**
     * Constructs an Address instance with mandatory fields.
     * Performs basic validation to ensure data consistency.
     */
    public Address(String country, String city, String street, String postalCode) {
        if (country == null || country.trim().isEmpty()) {
            throw new InvalidAddressException("Country cannot be empty.");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new InvalidAddressException("City cannot be empty.");
        }
        if (street == null || street.trim().isEmpty()) {
            throw new InvalidAddressException("Street cannot be empty.");
        }
        if (postalCode == null || !POSTAL_CODE_PATTERN.matcher(postalCode).matches()) {
            throw new InvalidAddressException("Postal code is invalid.");
        }
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    // Getters for address fields

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Returns a formatted string representation of the address.
     */
    @Override
    public String toString() {
        return street + ", " + city + ", " + country + " - " + postalCode;
    }

    /**
     * Checks equality based on address field values.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return country.equals(address.country) &&
            city.equals(address.city) &&
            street.equals(address.street) &&
            postalCode.equals(address.postalCode);
    }

    /**
     * Generates a hash code based on address properties.
     */
    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, postalCode);
    }
}
