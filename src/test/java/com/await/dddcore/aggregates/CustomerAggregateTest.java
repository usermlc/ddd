package com.await.dddcore.aggregates;

import com.await.dddcore.entities.Order;
import com.await.dddcore.entities.Customer;
import com.await.dddcore.valueobjects.Address;
import com.await.dddcore.valueobjects.Email;
import com.await.dddcore.valueobjects.Name;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerAggregateTest {

    @Test
    void shouldCreateValidCustomerAggregate() {
        UUID id = UUID.randomUUID();
        Name name = new Name("John", "Doe");
        Email email = new Email("john.doe@example.com");
        Address address = new Address("Ukraine", "Kyiv", "Street 1", "12345");

        CustomerAggregate customerAggregate = new CustomerAggregate(id, name, email, address);

        assertEquals(id, customerAggregate.getId());
        assertEquals(name, customerAggregate.getName());
        assertEquals(email, customerAggregate.getEmail());
        assertEquals(address, customerAggregate.getAddress());
    }

    @Test
    void shouldAddOrderToCustomerAggregate() {
        UUID id = UUID.randomUUID();
        Name name = new Name("John", "Doe");
        Email email = new Email("john.doe@example.com");
        Address address = new Address("Ukraine", "Kyiv", "Street 1", "12345");
        CustomerAggregate customerAggregate = new CustomerAggregate(id, name, email, address);

        Customer customer = new Customer(id, name, email, address);
        Order order = new Order(UUID.randomUUID(), customer, address);

        customerAggregate.addOrder(order);

        assertEquals(1, customerAggregate.getOrders().size());
    }

    @Test
    void shouldUpdateAddressInCustomerAggregate() {
        UUID id = UUID.randomUUID();
        Name name = new Name("John", "Doe");
        Email email = new Email("john.doe@example.com");
        Address oldAddress = new Address("Ukraine", "Kyiv", "Street 1", "12345");
        Address newAddress = new Address("Ukraine", "Lviv", "Street 2", "67890");

        CustomerAggregate customerAggregate = new CustomerAggregate(id, name, email, oldAddress);
        customerAggregate.updateAddress(newAddress);

        assertEquals(newAddress, customerAggregate.getAddress());
    }

    @Test
    void shouldReturnTrueForActiveOrders() {
        UUID id = UUID.randomUUID();
        Name name = new Name("John", "Doe");
        Email email = new Email("john.doe@example.com");
        Address address = new Address("Ukraine", "Kyiv", "Street 1", "12345");
        CustomerAggregate customerAggregate = new CustomerAggregate(id, name, email, address);

        Customer customer = new Customer(id, name, email, address);
        Order order = new Order(UUID.randomUUID(), customer, address);
        customerAggregate.addOrder(order);

        assertTrue(customerAggregate.hasActiveOrders());
    }
}
