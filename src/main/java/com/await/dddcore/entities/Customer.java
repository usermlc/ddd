package com.await.dddcore.entities;

import com.await.dddcore.valueobjects.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a customer entity in the system.
 * Holds personal details, address, and order history.
 */
public class Customer {

    private final UUID id; // Unique customer identifier
    private final Name name; // Customer's full name
    private final Email email; // Customer's email address
    private Address address; // Customer's address (modifiable)
    private final List<Order> orders; // List of customer's orders

    /**
     * Constructs a new customer with mandatory details.
     * Ensures all fields are properly initialized.
     */
    public Customer(UUID id, Name name, Email email, Address address) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.address = Objects.requireNonNull(address, "Address cannot be null");
        this.orders = new ArrayList<>();
    }

    // Getters for customer attributes

    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable copy of the order list to prevent modification.
     */
    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    /**
     * Updates the customer's address.
     * Ensures that the new address is not null.
     */
    public void changeAddress(Address newAddress) {
        this.address = Objects.requireNonNull(newAddress, "New address cannot be null");
    }

    /**
     * Adds an order to the customer's order history.
     * Ensures the order is not null before adding.
     */
    public void addOrder(Order order) {
        orders.add(Objects.requireNonNull(order, "Order cannot be null"));
    }

    /**
     * Checks if the customer has any active orders (not yet delivered).
     */
    public boolean hasActiveOrders() {
        return orders.stream().anyMatch(order -> order.getStatus() != OrderStatus.DELIVERED);
    }

    /**
     * Returns a string representation of the customer.
     */
    @Override
    public String toString() {
        return "Customer{id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + '}';
    }
}
