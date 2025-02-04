package com.await.dddcore.valueobjects;

import com.await.dddcore.exceptions.InvalidOrderItemException;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents the details of an item in an order, including the product ID, quantity, and price.
 * Ensures that each order item has valid attributes before being used.
 */
public final class OrderItemDetails {

    private final UUID productId;  // The unique identifier for the product
    private final int quantity;    // The quantity of the product in the order
    private final Money price;     // The price of a single unit of the product

    /**
     * Constructs an OrderItemDetails object with product ID, quantity, and price.
     * Throws an exception if any of the input values are invalid.
     *
     * @param productId The unique identifier for the product
     * @param quantity The quantity of the product
     * @param price The price of a single unit of the product
     */
    public OrderItemDetails(UUID productId, int quantity, Money price) {
        if (productId == null) {
            throw new InvalidOrderItemException("Product ID cannot be null.");
        }
        if (quantity < 1) {
            throw new InvalidOrderItemException("Quantity must be at least 1.");
        }
        if (price == null) {
            throw new InvalidOrderItemException("Price cannot be null.");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Calculates the total price for this order item by multiplying the unit price by the quantity.
     *
     * @return The total price for this order item
     */
    public Money getTotalPrice() {
        // Multiply the price by the quantity and return a new Money object representing the total price
        return new Money(price.getCurrency(), price.getAmount().multiply(
            java.math.BigDecimal.valueOf(quantity)));
    }

    /**
     * Returns the product ID for this order item.
     *
     * @return The unique product ID
     */
    public UUID getProductId() {
        return productId;
    }

    /**
     * Returns the quantity of the product in the order.
     *
     * @return The quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the price of a single unit of the product.
     *
     * @return The price of the product
     */
    public Money getPrice() {
        return price;
    }

    /**
     * Compares two OrderItemDetails objects for equality.
     * Two order items are considered equal if their product ID, quantity, and price are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemDetails that)) return false;
        return quantity == that.quantity &&
            productId.equals(that.productId) &&
            price.equals(that.price);
    }

    /**
     * Generates a hash code for the OrderItemDetails object based on its product ID, quantity, and price.
     */
    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity, price);
    }

    /**
     * Returns a string representation of the OrderItemDetails object.
     * Includes the product ID, quantity, and price.
     */
    @Override
    public String toString() {
        return "OrderItemDetails{" +
            "productId=" + productId +
            ", quantity=" + quantity +
            ", price=" + price +
            '}';
    }
}
