package com.await.dddcore.aggregates;

import com.await.dddcore.valueobjects.Address;
import com.await.dddcore.valueobjects.Money;
import com.await.dddcore.valueobjects.OrderItemDetails;
import com.await.dddcore.valueobjects.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Order aggregate representing a customer's order.
 * Contains order details, shipping address, status, and total price calculation.
 */
public class OrderAggregate {

    private final UUID id; // Unique order identifier
    private Address shippingAddress; // Shipping address for the order
    private Money totalPrice; // Total price of the order
    private OrderStatus status; // Current order status
    private final List<OrderItemDetails> orderItems; // List of items in the order

    /**
     * Creates a new order with an initial address and status.
     * The total price is initialized to zero.
     */
    public OrderAggregate(UUID id, Address shippingAddress) {
        this.id = id;
        this.shippingAddress = shippingAddress;
        this.totalPrice = new Money("USD", BigDecimal.ZERO);
        this.status = OrderStatus.NEW;
        this.orderItems = new ArrayList<>();
    }

    // Getters for order details

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItemDetails> getOrderItems() {
        return orderItems;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Adds an item to the order and updates the total price.
     */
    public void addOrderItem(OrderItemDetails item) {
        orderItems.add(item);
        recalculateTotalPrice();
    }

    /**
     * Recalculates the total order price based on item prices and quantities.
     */
    private void recalculateTotalPrice() {
        BigDecimal total = orderItems.stream()
            .map(item -> item.getPrice().getAmount().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        totalPrice = new Money("USD", total);
    }

    /**
     * Updates the shipping address for the order.
     */
    public void updateShippingAddress(Address newAddress) {
        this.shippingAddress = newAddress;
    }

    /**
     * Changes the order status (e.g., from NEW to SHIPPED).
     */
    public void changeStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }
}
