package com.await.dddcore.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import com.await.dddcore.exceptions.InvalidStockOperationException;
import org.junit.jupiter.api.Test;

class StockTest {

    @Test
    void shouldCreateValidStock() {
        Stock stock = new Stock(100);
        assertEquals(100, stock.getQuantity());
    }

    @Test
    void shouldThrowExceptionForNegativeStock() {
        assertThrows(InvalidStockOperationException.class, () -> new Stock(-1));
    }

    @Test
    void shouldReduceStockCorrectly() {
        Stock stock = new Stock(100);
        Stock reducedStock = stock.reduceStock(10);
        assertEquals(90, reducedStock.getQuantity());
    }

    @Test
    void shouldThrowExceptionWhenNotEnoughStock() {
        Stock stock = new Stock(100);
        assertThrows(InvalidStockOperationException.class, () -> stock.reduceStock(101));
    }
}
