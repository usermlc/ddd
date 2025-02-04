package com.await.dddcore.valueobjects;

import com.await.dddcore.exceptions.DimensionExceededException;
import com.await.dddcore.exceptions.InvalidDimensionException;
import java.util.Objects;

/**
 * Represents an immutable value object for dimensions.
 * Ensures validity and enforces constraints on maximum allowed size.
 */
public final class Dimensions {

    private final double length; // Length of the object
    private final double width;  // Width of the object
    private final double height; // Height of the object

    // Maximum allowable dimensions to maintain consistency
    public static final double MAX_LENGTH = 100.0;
    public static final double MAX_WIDTH = 100.0;
    public static final double MAX_HEIGHT = 100.0;

    /**
     * Constructs a Dimensions instance with specified values.
     * Validates input to ensure non-negative values and size constraints.
     *
     * @param length The length of the object
     * @param width  The width of the object
     * @param height The height of the object
     * @throws InvalidDimensionException if any dimension is zero or negative
     * @throws DimensionExceededException if any dimension exceeds the max allowed size
     */
    public Dimensions(double length, double width, double height) {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new InvalidDimensionException("Dimensions must be greater than zero.");
        }
        this.length = length;
        this.width = width;
        this.height = height;
        validateMaxDimensions();
    }

    /**
     * Checks if the dimensions exceed predefined maximum limits.
     *
     * @throws DimensionExceededException if any dimension exceeds the max allowed size
     */
    private void validateMaxDimensions() {
        if (length > MAX_LENGTH || width > MAX_WIDTH || height > MAX_HEIGHT) {
            throw new DimensionExceededException("Dimensions exceed the maximum allowed size.");
        }
    }

    /**
     * Calculates the volume of the object based on its dimensions.
     *
     * @return The volume as length * width * height
     */
    public double calculateVolume() {
        return length * width * height;
    }

    // Getters for each dimension

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Checks equality based on all three dimensions.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dimensions that)) return false;
        return Double.compare(that.length, length) == 0 &&
            Double.compare(that.width, width) == 0 &&
            Double.compare(that.height, height) == 0;
    }

    /**
     * Generates a hash code based on the dimensions.
     */
    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }

    /**
     * Returns a formatted string representation of the dimensions.
     */
    @Override
    public String toString() {
        return "Dimensions{" +
            "length=" + length +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
