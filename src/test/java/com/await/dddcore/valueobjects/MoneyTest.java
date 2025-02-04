package com.await.dddcore.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import com.await.dddcore.exceptions.InvalidMoneyOperationException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class MoneyTest {

    @Test
    void shouldCreateValidMoney() {
        Money money = new Money("USD", new BigDecimal("100.00"));
        assertEquals("USD", money.getCurrency());
        assertEquals(new BigDecimal("100.00"), money.getAmount());
    }

    @Test
    void shouldAddMoneyCorrectly() {
        Money money1 = new Money("USD", new BigDecimal("50.00"));
        Money money2 = new Money("USD", new BigDecimal("50.00"));
        Money result = money1.add(money2);
        assertEquals(new BigDecimal("100.00"), result.getAmount());
    }

    @Test
    void shouldThrowExceptionForDifferentCurrencies() {
        Money money1 = new Money("USD", new BigDecimal("50.00"));
        Money money2 = new Money("EUR", new BigDecimal("50.00"));
        assertThrows(InvalidMoneyOperationException.class, () -> money1.add(money2));
    }
}
