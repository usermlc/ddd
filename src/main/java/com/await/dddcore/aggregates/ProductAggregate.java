package com.await.dddcore.aggregates;

import com.await.dddcore.valueobjects.Money;
import com.await.dddcore.valueobjects.ProductDetails;
import com.await.dddcore.valueobjects.Stock;

import java.util.UUID;

/**
 * Product aggregate representing a single product in the system.
 * It contains product details, price, and stock management.
 */
public class ProductAggregate {

    private final UUID id; // Unique product identifier
    private final ProductDetails details; // Product-specific details (name, description, etc.)
    private Money price; // Product price
    private Stock stock; // Stock information

    /**
     * Initializes a new product with its details, price, and stock level.
     */
    public ProductAggregate(UUID id, ProductDetails details, Money price, Stock stock) {
        this.id = id;
        this.details = details;
        this.price = price;
        this.stock = stock;
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
     * Reduces stock by a given quantity if enough stock is available.
     * Returns true if the stock was successfully reduced, otherwise false.
     */
    public boolean reduceStock(int quantity) {
        if (stock.getQuantity() >= quantity) {
            stock = stock.reduceStock(quantity);
            return true;
        }
        return false;
    }

    /**
     * Updates the product price.
     */
    public void updatePrice(Money newPrice) {
        this.price = newPrice;
    }
}
