This project implements a simple microservices-based Search Service using Java and Spring Boot.

The application consists of two services:

1] Inventory Service – Stores product data in MongoDB and exposes a REST API to fetch products.
2] Search Service – Calls the Inventory Service, filters products priced under $50 using Java Stream API, and returns a sorted list of product names.

The project demonstrates basic microservices communication, REST API integration, and MongoDB usage.

Tech Stack :
        Java 17
        Spring Boot
        MongoDB
        Maven
        REST APIs


Project Structure :
      Java_SearchService
       ├── inventory-service
       ├── search-service
       └── README.md

Inventory Service:
      Connects to MongoDB
      Stores product data
      API to fetch all products

Endpoint
    GET http://localhost:8081/products

Search Service: 
    Calls Inventory Service API
    Filters products with price < 50
    Returns only product names
    Sorted alphabetically

Endpoint
    GET http://localhost:8082/search


