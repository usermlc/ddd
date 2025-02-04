package com.await.dddcore.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import com.await.dddcore.exceptions.InvalidOrderItemException;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import java.math.BigDecimal;

class OrderItemDetailsTest {

    @Test
    void shouldCreateValidOrderItemDetails() {
        UUID productId = UUID.randomUUID();
        Money price = new Money("USD", new BigDecimal("10.00"));
        OrderItemDetails item = new OrderItemDetails(productId, 2, price);
        assertEquals(productId, item.getProductId());
        assertEquals(2, item.getQuantity());
        assertEquals(price, item.getPrice());
    }

    @Test
    void shouldThrowExceptionForNegativeQuantity() {
        UUID productId = UUID.randomUUID();
        Money price = new Money("USD", new BigDecimal("10.00"));
        assertThrows(InvalidOrderItemException.class, () -> new OrderItemDetails(productId, -1, price));
    }

    @Test
    void shouldThrowExceptionForNullPrice() {
        UUID productId = UUID.randomUUID();
        assertThrows(InvalidOrderItemException.class, () -> new OrderItemDetails(productId, 2, null));
    }
}
