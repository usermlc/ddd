package com.await.dddcore.valueobjects;

import com.await.dddcore.exceptions.InvalidProductDetailsException;
import java.util.Objects;

/**
 * Represents the details of a product, including its name, description, and dimensions.
 * Ensures that product details are valid before being used.
 */
public final class ProductDetails {

    private final String name;       // The name of the product
    private final String description; // The description of the product
    private final Dimensions dimensions; // The dimensions of the product

    /**
     * Constructs a ProductDetails object with product name, description, and dimensions.
     * Throws an exception if any of the input values are invalid.
     *
     * @param name The name of the product
     * @param description The description of the product
     * @param dimensions The dimensions of the product
     */
    public ProductDetails(String name, String description, Dimensions dimensions) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductDetailsException("Product name cannot be empty.");
        }
        this.name = name;
        this.description = description;
        this.dimensions = dimensions;
    }

    /**
     * Returns the name of the product.
     *
     * @return The product name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the product.
     *
     * @return The product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the dimensions of the product.
     *
     * @return The product dimensions
     */
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * Returns a short description of the product.
     * If the description is longer than 50 characters, it truncates and adds "..." at the end.
     *
     * @return A truncated or full description of the product
     */
    public String getShortDescription() {
        if (description.length() > 50) {
            return description.substring(0, 50) + "...";
        }
        return description;
    }

    /**
     * Compares two ProductDetails objects for equality.
     * Two product details are considered equal if their name, description, and dimensions are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDetails that)) return false;
        return name.equals(that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(dimensions, that.dimensions);
    }

    /**
     * Generates a hash code for the ProductDetails object based on its name, description, and dimensions.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description, dimensions);
    }

    /**
     * Returns a string representation of the ProductDetails object.
     * Includes the product name, description, and dimensions.
     */
    @Override
    public String toString() {
        return "ProductDetails{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", dimensions=" + dimensions +
            '}';
    }
}
