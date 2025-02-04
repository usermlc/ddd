package com.await.dddcore.aggregates;

import com.await.dddcore.valueobjects.Address;
import com.await.dddcore.valueobjects.Money;
import com.await.dddcore.valueobjects.OrderItemDetails;
import com.await.dddcore.valueobjects.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderAggregateTest {

    @Test
    void shouldCreateValidOrderAggregate() {
        UUID orderId = UUID.randomUUID();
        Address shippingAddress = new Address("Ukraine", "Kyiv", "Street 1", "12345");
        OrderAggregate orderAggregate = new OrderAggregate(orderId, shippingAddress);

        assertEquals(orderId, orderAggregate.getId());
        assertEquals(shippingAddress, orderAggregate.getShippingAddress());
        assertEquals(OrderStatus.NEW, orderAggregate.getStatus());
        assertEquals(new Money("USD", BigDecimal.ZERO), orderAggregate.getTotalPrice());
    }

    @Test
    void shouldAddItemToOrderAggregate() {
        UUID orderId = UUID.randomUUID();
        Address shippingAddress = new Address("Ukraine", "Kyiv", "Street 1", "12345");
        OrderAggregate orderAggregate = new OrderAggregate(orderId, shippingAddress);

        OrderItemDetails item = new OrderItemDetails(UUID.randomUUID(), 2, new Money("USD", new BigDecimal("50.00")));
        orderAggregate.addOrderItem(item);

        assertEquals(1, orderAggregate.getOrderItems().size());
        assertEquals(new Money("USD", new BigDecimal("100.00")), orderAggregate.getTotalPrice());
    }

    @Test
    void shouldUpdateShippingAddress() {
        UUID orderId = UUID.randomUUID();
        Address oldAddress = new Address("Ukraine", "Kyiv", "Street 1", "12345");
        Address newAddress = new Address("Ukraine", "Lviv", "Street 2", "67890");
        OrderAggregate orderAggregate = new OrderAggregate(orderId, oldAddress);

        orderAggregate.updateShippingAddress(newAddress);

        assertEquals(newAddress, orderAggregate.getShippingAddress());
    }

    @Test
    void shouldChangeOrderStatus() {
        UUID orderId = UUID.randomUUID();
        Address shippingAddress = new Address("Ukraine", "Kyiv", "Street 1", "12345");
        OrderAggregate orderAggregate = new OrderAggregate(orderId, shippingAddress);

        orderAggregate.changeStatus(OrderStatus.CONFIRMED);

        assertEquals(OrderStatus.CONFIRMED, orderAggregate.getStatus());
    }
}
