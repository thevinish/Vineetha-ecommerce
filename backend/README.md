# Vineetha E-commerce Backend

A comprehensive Spring Boot backend for the Vineetha E-commerce Platform, built by Vinish.

## 🚀 Features

- **User Management**: Registration, authentication, profile management
- **Product Management**: CRUD operations, categories, inventory
- **Shopping Cart**: Add/remove items, quantity management
- **Order Management**: Order processing, tracking, status updates
- **Review System**: Product reviews and ratings
- **Admin Dashboard**: Analytics, user management, order tracking
- **JWT Authentication**: Secure token-based authentication
- **RESTful APIs**: Clean, documented API endpoints
- **MySQL Database**: Reliable data persistence
- **Swagger Documentation**: Interactive API documentation

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security** with JWT
- **Spring Data JPA**
- **MySQL 8.0**
- **Maven**
- **Swagger/OpenAPI**

## 📋 Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🗄️ Database Setup

1. **Create MySQL Database**:
```sql
CREATE DATABASE vineetha_db;
CREATE USER 'vineetha_user'@'localhost' IDENTIFIED BY 'vineetha_password';
GRANT ALL PRIVILEGES ON vineetha_db.* TO 'vineetha_user'@'localhost';
FLUSH PRIVILEGES;
```

2. **Update Configuration**:
   - Edit `src/main/resources/application.properties`
   - Update database credentials if needed
   - Configure JWT secret key

## 🚀 Running the Application

### Method 1: Using Maven
```bash
cd vineetha-backend
mvn spring-boot:run
```

### Method 2: Using IDE
- Import the project as a Maven project
- Run `VineethaApplication.java`

### Method 3: Using JAR
```bash
mvn clean package
java -jar target/vineetha-ecommerce-1.0.0.jar
```

## 📡 API Endpoints

### Base URL
```
http://localhost:8080/api/v1
```

### Authentication
- `POST /auth/register` - User registration
- `POST /auth/login` - User login
- `POST /auth/admin-login` - Admin login
- `POST /auth/refresh` - Refresh token

### Products
- `GET /products` - Get all products (with pagination)
- `GET /products/{id}` - Get product by ID
- `GET /products/category/{category}` - Get products by category
- `GET /products/search` - Search products
- `POST /products` - Create product (Admin only)
- `PUT /products/{id}` - Update product (Admin only)
- `DELETE /products/{id}` - Delete product (Admin only)

### Users
- `GET /users/profile` - Get user profile
- `PUT /users/profile` - Update user profile
- `GET /users/orders` - Get user orders
- `GET /users/cart` - Get user cart

### Cart
- `GET /cart` - Get cart items
- `POST /cart/add` - Add item to cart
- `PUT /cart/{itemId}` - Update cart item
- `DELETE /cart/{itemId}` - Remove cart item
- `POST /cart/clear` - Clear cart

### Orders
- `POST /orders` - Create order
- `GET /orders/{id}` - Get order details
- `GET /orders/track/{orderNumber}` - Track order
- `PUT /orders/{id}/status` - Update order status (Admin only)

### Reviews
- `GET /products/{id}/reviews` - Get product reviews
- `POST /products/{id}/reviews` - Add review
- `PUT /reviews/{id}` - Update review
- `DELETE /reviews/{id}` - Delete review

### Admin
- `GET /admin/dashboard` - Get dashboard stats
- `GET /admin/users` - Get all users
- `GET /admin/orders` - Get all orders
- `GET /admin/products` - Get all products
- `POST /admin/products` - Create product
- `PUT /admin/products/{id}` - Update product
- `DELETE /admin/products/{id}` - Delete product

## 🔐 Authentication

The application uses JWT (JSON Web Tokens) for authentication.

### Request Headers
```
Authorization: Bearer <jwt_token>
Content-Type: application/json
```

### Token Structure
- **Access Token**: Valid for 24 hours
- **Refresh Token**: Valid for 7 days

## 📊 Database Schema

### Core Entities
- **User**: Customer and admin accounts
- **Product**: Product catalog with categories
- **Order**: Order management and tracking
- **OrderItem**: Individual items in orders
- **CartItem**: Shopping cart functionality
- **Review**: Product reviews and ratings

### Enums
- **UserRole**: CUSTOMER, ADMIN, MODERATOR
- **UserStatus**: ACTIVE, INACTIVE, SUSPENDED, PENDING_VERIFICATION
- **ProductCategory**: ELECTRONICS, CLOTHING, FOOD_BEVERAGES, etc.
- **ProductStatus**: ACTIVE, INACTIVE, OUT_OF_STOCK, etc.
- **OrderStatus**: PENDING, CONFIRMED, PROCESSING, SHIPPED, etc.
- **PaymentStatus**: PENDING, PAID, FAILED, REFUNDED
- **PaymentMethod**: CASH_ON_DELIVERY, UPI, CREDIT_CARD, etc.
- **ReviewStatus**: PENDING, APPROVED, REJECTED, SPAM

## 🔧 Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/vineetha_db
spring.datasource.username=vineetha_user
spring.datasource.password=vineetha_password

# JWT
jwt.secret=your-secret-key
jwt.expiration=86400000
jwt.refresh-expiration=604800000

# Server
server.port=8080
server.servlet.context-path=/api/v1

# CORS
cors.allowed-origins=http://localhost:3000,http://localhost:8080
cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
cors.allowed-headers=*
```

## 📚 API Documentation

Once the application is running, access Swagger UI at:
```
http://localhost:8080/api/v1/swagger-ui.html
```

## 🧪 Testing

### Run Tests
```bash
mvn test
```

### Test Coverage
```bash
mvn jacoco:report
```

## 📁 Project Structure

```
src/main/java/com/vineetha/
├── VineethaApplication.java          # Main application class
├── config/                          # Configuration classes
│   └── SecurityConfig.java         # Security configuration
├── controller/                      # REST controllers
├── model/                          # Entity classes
│   ├── User.java
│   ├── Product.java
│   ├── Order.java
│   ├── CartItem.java
│   ├── Review.java
│   └── enums/
├── repository/                      # Data access layer
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   ├── OrderRepository.java
│   ├── CartItemRepository.java
│   └── ReviewRepository.java
├── service/                         # Business logic layer
└── util/                           # Utility classes
    ├── JwtUtil.java
    └── JwtAuthenticationFilter.java
```

## 🚀 Deployment

### Production Deployment
1. Update `application.properties` for production environment
2. Set up production MySQL database
3. Configure environment variables
4. Build and deploy JAR file

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/vineetha-ecommerce-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📄 License

This project is built by Vinish for the Vineetha E-commerce Platform.

## 📞 Support

For support and questions:
- Email: support@vineetha.in
- Documentation: Check the Swagger UI
- Issues: Create an issue in the repository

---

**Built with ❤️ by Vinish** 