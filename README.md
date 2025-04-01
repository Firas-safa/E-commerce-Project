# Full Stack Developer Entry Exam - E-Commerce Project

## Overview

This project is a simplified e-commerce application that includes backend development. It features authentication, an admin panel for managing products and categories with drag-and-drop ordering.

## Features

### 1. Authentication

- User registration and login.
- Password handling with BCrypt for encryption.
- JWT-based authentication for enhanced security.

### 2. Admin Panel (Product & Category Management)

- CRUD operations for products (title, description, price) and categories (title, description).
- Drag-and-drop functionality to reorder products and categories.
- Persistent ordering stored in the database.

## Tech Stack

### **Backend**

- **Framework:** Spring Boot
- **Database:** PostgreSQL;
- **API:** REST endpoints for authentication, product/category management, ordering, and cart.
-  JWT-based authentication.

## Setup & Installation

Follow these steps to clone the project and run it locally:

### **1. Clone the Repository**

 git clone https://github.com/Firas-safa/E-commerce-Project.git

 cd ecommerce-project

### **2. Set Up the Backend**

#### **a. Navigate to the backend folder**

 cd backend

#### **b. Configure the Database**

- Update `application.properties` with your database configuration.
- Example for PostgreSQL:

```properties
 spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
 spring.datasource.username=your_username
 spring.datasource.password=your_password
```

#### **c. Build & Run the Backend**

```sh
 mvn clean install   # If using Maven
 ./mvnw spring-boot:run  # Runs the backend server
```

### **4. Test Authentication & Features**

- Register a new user.
- Log in and access the product categories.
- Use the admin panel to add, update, delete, and reorder products and categories.
- Try the drag-and-drop functionality and verify that ordering is saved.

## Additional Notes

- If using JWT authentication, ensure the frontend sends authentication tokens in API requests.
- If running the project in production, configure environment variables properly.
- Ensure the database is running before starting the backend server.


## License

This project is for educational and evaluation purposes only. Feel free to modify and improve it.

---

If you have any issues or questions, feel free to reach out!

