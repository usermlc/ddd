package com.await.dddcore.entities;

import com.await.dddcore.valueobjects.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a product in the system.
 * Contains product details, price, and stock management.
 */
public class Product {

    private final UUID id; // Unique identifier for the product
    private final ProductDetails details; // Product-specific details (name, description, etc.)
    private Money price; // Product price
    private Stock stock; // Stock information

    /**
     * Constructs a new product with mandatory attributes.
     */
    public Product(UUID id, ProductDetails details, Money price, Stock stock) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.details = Objects.requireNonNull(details, "Details cannot be null");
        this.price = Objects.requireNonNull(price, "Price cannot be null");
        this.stock = Objects.requireNonNull(stock, "Stock cannot be null");
    }

    // Getters for product attributes

    public UUID getId() {
        return id;
    }

    public ProductDetails getDetails() {
        return details;
    }

    public Money getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }

    /**
     * Updates the price of the product.
     * Ensures the new price is positive before applying changes.
     */
    public void updatePrice(Money newPrice) {
        if (newPrice.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.price = newPrice;
    }

    /**
     * Reduces stock by a specified quantity.
     * Throws an exception if there is insufficient stock.
     */
    public void reduceStock(int quantity) {
        stock = stock.reduceStock(quantity);
    }

    /**
     * Checks if there is enough stock available for a given quantity.
     */
    public boolean hasSufficientStock(int quantity) {
        return stock.getQuantity() >= quantity;
    }

    /**
     * Returns a string representation of the product.
     */
    @Override
    public String toString() {
        return "Product{id=" + id + ", details=" + details + ", price=" + price + ", stock=" + stock + '}';
    }
}
