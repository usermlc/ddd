package com.await.dddcore.valueobjects;

import com.await.dddcore.exceptions.InvalidStockOperationException;
import java.util.Objects;

public class Stock {

    private final int quantity;

    public Stock(int quantity) {
        if (quantity < 0) {
            throw new InvalidStockOperationException("Stock quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Stock reduceStock(int amount) {
        if (amount <= 0) {
            throw new InvalidStockOperationException("Amount to reduce must be positive");
        }
        if (amount > quantity) {
            throw new InvalidStockOperationException("Not enough stock available");
        }
        return new Stock(this.quantity - amount);
    }

    public Stock addStock(int amount) {
        if (amount <= 0) {
            throw new InvalidStockOperationException("Amount to add must be positive");
        }
        return new Stock(this.quantity + amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock stock)) return false;
        return quantity == stock.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }

    @Override
    public String toString() {
        return "Stock{quantity=" + quantity + '}';
    }
}
