# Customer Service - PaymentChain

This repository contains the **Customer Service** of the **PaymentChain** system.

The service is built using **Hexagonal Architecture (Ports and Adapters)** to ensure clear separation of concerns, better maintainability, and testability.

## 🚀 Features

- Register new customers
- Retrieve customer information by ID
- Update and delete customers
- Validate email and phone number uniqueness
- Pagination and sorting support
- Exception handling

## 🧱 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 (in-memory database)
- MapStruct
- Lombok

## 📐 Architecture

This project follows **Hexagonal Architecture**, with:

- **Domain Layer**: Business logic and domain models  
- **Application Layer**: Use cases and service ports  
- **Infrastructure Layer**: Database adapters (JPA), mappers, configurations, and repositories  
- **API Layer** (optional in your project): Controllers or REST interfaces  

## ⚙️ How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/paymentchain-customer-service.git
   cd paymentchain-customer-service
