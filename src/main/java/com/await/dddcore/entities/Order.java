package com.await.dddcore.entities;

import com.await.dddcore.valueobjects.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents an order placed by a customer.
 * Contains details about the customer, items, total price, status, and shipping address.
 */
public class Order {

    private final UUID id; // Unique order identifier
    private final Customer customer; // Customer who placed the order
    private final List<OrderItemDetails> items; // List of items in the order
    private Money totalPrice; // Total price of the order
    private OrderStatus status; // Current status of the order
    private Address shippingAddress; // Shipping address for the order

    /**
     * Creates a new order with a customer and a shipping address.
     * The order starts with no items, a total price of zero, and a NEW status.
     */
    public Order(UUID id, Customer customer, Address shippingAddress) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.customer = Objects.requireNonNull(customer, "Customer cannot be null");
        this.items = new ArrayList<>();
        this.totalPrice = new Money("USD", BigDecimal.ZERO);
        this.status = OrderStatus.NEW;
        this.shippingAddress = Objects.requireNonNull(shippingAddress, "Shipping address cannot be null");
    }

    // Getters for order details

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns an immutable list of order items to prevent modifications.
     */
    public List<OrderItemDetails> getItems() {
        return List.copyOf(items);
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Adds an item to the order and updates the total price.
     */
    public void addItem(OrderItemDetails item) {
        items.add(Objects.requireNonNull(item, "Item cannot be null"));
        totalPrice = totalPrice.add(item.getTotalPrice());
    }

    /**
     * Changes the status of the order.
     * Prevents reverting from SHIPPED to any previous status.
     */
    public void changeStatus(OrderStatus newStatus) {
        if (status == OrderStatus.SHIPPED && newStatus != OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot revert from SHIPPED status.");
        }
        this.status = newStatus;
    }

    /**
     * Updates the shipping address if the order has not yet shipped.
     */
    public void changeShippingAddress(Address newAddress) {
        if (status == OrderStatus.SHIPPED || status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot change address after shipping.");
        }
        this.shippingAddress = Objects.requireNonNull(newAddress, "New address cannot be null");
    }

    /**
     * Returns a string representation of the order.
     */
    @Override
    public String toString() {
        return "Order{id=" + id + ", customer=" + customer + ", totalPrice=" + totalPrice + ", status=" + status + '}';
    }
}
