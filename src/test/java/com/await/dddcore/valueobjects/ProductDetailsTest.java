package com.await.dddcore.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import com.await.dddcore.exceptions.InvalidProductDetailsException;
import org.junit.jupiter.api.Test;

class ProductDetailsTest {

    @Test
    void shouldCreateValidProductDetails() {
        Dimensions dimensions = new Dimensions(10, 10, 10);
        ProductDetails productDetails = new ProductDetails("Product 1", "Product Description", dimensions);
        assertEquals("Product 1", productDetails.getName());
        assertEquals("Product Description", productDetails.getDescription());
        assertEquals(dimensions, productDetails.getDimensions());
    }

    @Test
    void shouldThrowExceptionForEmptyName() {
        Dimensions dimensions = new Dimensions(10, 10, 10);
        assertThrows(InvalidProductDetailsException.class, () -> new ProductDetails("", "Product Description", dimensions));
    }
}
