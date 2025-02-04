package com.await.dddcore.valueobjects;

import com.await.dddcore.exceptions.InvalidMoneyOperationException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Objects;

/**
 * Represents a monetary value with a specific currency.
 * Ensures operations on money values are consistent and valid.
 */
public final class Money {

    private final String currency;  // The currency of the monetary amount (e.g., USD, EUR)
    private final BigDecimal amount; // The amount of money in the specified currency

    /**
     * Constructs a Money object with a specific currency and amount.
     * Throws an exception if the currency is null or empty, or if the amount is null.
     *
     * @param currency The currency of the amount (e.g., USD, EUR)
     * @param amount The amount of money
     */
    public Money(String currency, BigDecimal amount) {
        if (currency == null || currency.trim().isEmpty()) {
            throw new InvalidMoneyOperationException("Currency cannot be null or empty.");
        }
        if (amount == null) {
            throw new InvalidMoneyOperationException("Amount cannot be null.");
        }
        this.currency = currency;
        this.amount = amount;
    }

    /**
     * Adds another Money object to the current one, ensuring the currencies match.
     *
     * @param other The Money object to add
     * @return A new Money object with the resulting amount
     * @throws InvalidMoneyOperationException if the currencies do not match
     */
    public Money add(Money other) {
        validateCurrency(other);
        return new Money(this.currency, this.amount.add(other.amount));
    }

    /**
     * Subtracts another Money object from the current one, ensuring the currencies match.
     *
     * @param other The Money object to subtract
     * @return A new Money object with the resulting amount
     * @throws InvalidMoneyOperationException if the currencies do not match
     */
    public Money subtract(Money other) {
        validateCurrency(other);
        return new Money(this.currency, this.amount.subtract(other.amount));
    }

    /**
     * Formats the amount of money according to the currency's format.
     *
     * @return A string representing the formatted monetary value
     */
    public String format() {
        Currency curr = Currency.getInstance(currency); // Get currency information
        NumberFormat format = NumberFormat.getCurrencyInstance(); // Get the currency format
        format.setCurrency(curr);
        return format.format(amount);
    }

    /**
     * Validates if two Money objects have the same currency before performing operations.
     *
     * @param other The other Money object to compare with
     * @throws InvalidMoneyOperationException if the currencies do not match
     */
    private void validateCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new InvalidMoneyOperationException("Cannot operate on different currencies.");
        }
    }

    // Getters for currency and amount

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Checks if two Money objects are equal based on their currency and amount.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return currency.equals(money.currency) && amount.equals(money.amount);
    }

    /**
     * Generates a hash code based on the currency and amount.
     */
    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    /**
     * Returns a string representation of the Money object.
     */
    @Override
    public String toString() {
        return "Money{" +
            "currency='" + currency + '\'' +
            ", amount=" + amount +
            '}';
    }
}
