# DDD Core

This project implements a Domain-Driven Design (DDD) architecture in Java, showcasing the use of value objects, aggregates, entities, and domain events in a typical e-commerce application.

## Features

- **Aggregates**: Representations of business entities that contain and manage other entities or value objects, such as `ProductAggregate` and `OrderAggregate`.
- **Entities**: Core business objects like `Product`, `Customer`, and `Order`.
- **Value Objects**: Immutable objects that hold data and enforce business rules, such as `Money`, `Dimensions`, and `Address`.
- **Validation**: Enforced domain rules, including validation for price, stock, email, name, and address.
- **Stock Management**: The `Stock` class ensures proper stock operations by controlling addition, reduction, and validation of stock quantities.
  
## Prerequisites

- Java 11 or later

## Installation

1. Download or import the project into your preferred IDE.
2. Build the project using Maven or Gradle:
   ```bash
   mvn clean install
   ```

## Usage

This project focuses on Domain-Driven Design principles for building e-commerce applications. The primary classes include:

- **Product Aggregate**: Manages product details, price, and stock.
- **Customer Entity**: Contains customer data and orders.
- **Order Entity**: Tracks customer orders, their items, and shipping details.
- **Money Value Object**: Handles money operations like addition and subtraction, ensuring currency consistency.

To interact with these classes, instantiate them and use provided methods for adding stock, updating prices, processing orders, and managing customer information.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
