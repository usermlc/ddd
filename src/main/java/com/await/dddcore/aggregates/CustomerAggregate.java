package com.await.dddcore.aggregates;

import com.await.dddcore.entities.Order;
import com.await.dddcore.valueobjects.Address;
import com.await.dddcore.valueobjects.Email;
import com.await.dddcore.valueobjects.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Customer aggregate that holds main customer data
 * along with their orders.
 */
public class CustomerAggregate {

    private final UUID id; // Unique customer identifier
    private final Name name; // Customer's name (value object)
    private final Email email; // Customer's email (value object)
    private Address address; // Customer's address (can be updated)
    private final List<Order> orders; // List of customer's orders

    /**
     * Constructor to initialize a customer with basic details.
     */
    public CustomerAggregate(UUID id, Name name, Email email, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.orders = new ArrayList<>();
    }

    /**
     * Adds a new order to the customer's order list.
     * Ensures that the order is not null before adding.
     */
    public void addOrder(Order order) {
        if (order != null) {
            this.orders.add(order);
        }
    }

    /**
     * Updates the customer's address.
     * Ensures the new address is not null before updating.
     */
    public void updateAddress(Address newAddress) {
        if (newAddress != null) {
            this.address = newAddress;
        }
    }

    /**
     * Checks if the customer has any active (non-completed) orders.
     */
    public boolean hasActiveOrders() {
        return orders.stream().anyMatch(order -> !order.getStatus().isCompleted());
    }

    /**
     * Returns an immutable copy of the order list to prevent modifications from outside.
     */
    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    // Getters for customer attributes

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public UUID getId() {
        return id;
    }
}
