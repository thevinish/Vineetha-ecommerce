# API Testing Guide for Vineetha E-commerce

## Prerequisites
- Backend server running on `http://localhost:8080`
- MySQL database set up and running
- Sample data loaded

## Base URL
```
http://localhost:8080/api
```

## Authentication
Most endpoints require JWT authentication. Include the token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

---

## 1. Authentication Endpoints

### 1.1 User Registration
**POST** `/auth/register`

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "phoneNumber": "9876543210"
  }'
```

**Expected Response:**
```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "id": 32,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "role": "CUSTOMER",
    "status": "ACTIVE"
  }
}
```

### 1.2 User Login
**POST** `/auth/login`

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "password123"
  }'
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": 32,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "role": "CUSTOMER"
    }
  }
}
```

### 1.3 Admin Login
**POST** `/auth/login`

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@vineetha.in",
    "password": "password"
  }'
```

---

## 2. Product Endpoints

### 2.1 Get All Products
**GET** `/products`

```bash
curl -X GET http://localhost:8080/api/products
```

**With Pagination:**
```bash
curl -X GET "http://localhost:8080/api/products?page=0&size=10"
```

**With Category Filter:**
```bash
curl -X GET "http://localhost:8080/api/products?category=ELECTRONICS"
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Products retrieved successfully",
  "data": {
    "content": [
      {
        "id": 1,
        "name": "iPhone 14 Pro",
        "description": "Latest iPhone with advanced camera system",
        "brand": "Apple",
        "price": 89999.00,
        "originalPrice": 99999.00,
        "stockQuantity": 50,
        "category": "ELECTRONICS",
        "status": "ACTIVE",
        "mainImage": "assets/images/products/iphone.jpg",
        "rating": 4.5,
        "reviewCount": 125
      }
    ],
    "totalElements": 50,
    "totalPages": 5,
    "currentPage": 0,
    "size": 10
  }
}
```

### 2.2 Get Product by ID
**GET** `/products/{id}`

```bash
curl -X GET http://localhost:8080/api/products/1
```

### 2.3 Search Products
**GET** `/products/search?q={query}`

```bash
curl -X GET "http://localhost:8080/api/products/search?q=iPhone"
```

### 2.4 Create Product (Admin Only)
**POST** `/products`

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <admin-token>" \
  -d '{
    "name": "New Product",
    "description": "Product description",
    "brand": "Brand Name",
    "price": 999.00,
    "originalPrice": 1299.00,
    "stockQuantity": 100,
    "category": "ELECTRONICS",
    "mainImage": "assets/images/products/new-product.jpg"
  }'
```

### 2.5 Update Product (Admin Only)
**PUT** `/products/{id}`

```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <admin-token>" \
  -d '{
    "name": "Updated Product Name",
    "price": 79999.00
  }'
```

### 2.6 Delete Product (Admin Only)
**DELETE** `/products/{id}`

```bash
curl -X DELETE http://localhost:8080/api/products/1 \
  -H "Authorization: Bearer <admin-token>"
```

---

## 3. Cart Endpoints

### 3.1 Get User Cart
**GET** `/cart`

```bash
curl -X GET http://localhost:8080/api/cart \
  -H "Authorization: Bearer <user-token>"
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Cart retrieved successfully",
  "data": {
    "items": [
      {
        "id": 1,
        "productId": 1,
        "quantity": 2,
        "unitPrice": 89999.00,
        "totalPrice": 179998.00,
        "product": {
          "id": 1,
          "name": "iPhone 14 Pro",
          "mainImage": "assets/images/products/iphone.jpg"
        }
      }
    ],
    "totalItems": 2,
    "totalAmount": 179998.00
  }
}
```

### 3.2 Add Item to Cart
**POST** `/cart/add`

```bash
curl -X POST http://localhost:8080/api/cart/add \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <user-token>" \
  -d '{
    "productId": 1,
    "quantity": 2
  }'
```

### 3.3 Update Cart Item Quantity
**PUT** `/cart/update`

```bash
curl -X PUT http://localhost:8080/api/cart/update \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <user-token>" \
  -d '{
    "productId": 1,
    "quantity": 3
  }'
```

### 3.4 Remove Item from Cart
**DELETE** `/cart/remove/{productId}`

```bash
curl -X DELETE http://localhost:8080/api/cart/remove/1 \
  -H "Authorization: Bearer <user-token>"
```

---

## 4. Order Endpoints

### 4.1 Create Order
**POST** `/orders`

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <user-token>" \
  -d '{
    "shippingAddress": "123 Main Street",
    "shippingCity": "Mumbai",
    "shippingState": "Maharashtra",
    "shippingPostalCode": "400001",
    "paymentMethod": "CREDIT_CARD",
    "notes": "Please deliver in the morning"
  }'
```

**Expected Response:**
```json
{
  "success": true,
  "message": "Order created successfully",
  "data": {
    "id": 26,
    "orderNumber": "ORD-2024-026",
    "totalAmount": 179998.00,
    "status": "PENDING",
    "paymentStatus": "PENDING",
    "shippingAddress": "123 Main Street",
    "shippingCity": "Mumbai",
    "shippingState": "Maharashtra",
    "shippingPostalCode": "400001",
    "createdAt": "2024-01-15T10:30:00"
  }
}
```

### 4.2 Get User Orders
**GET** `/orders`

```bash
curl -X GET http://localhost:8080/api/orders \
  -H "Authorization: Bearer <user-token>"
```

### 4.3 Get Order by ID
**GET** `/orders/{id}`

```bash
curl -X GET http://localhost:8080/api/orders/26 \
  -H "Authorization: Bearer <user-token>"
```

### 4.4 Update Order Tracking (Admin Only)
**PUT** `/orders/{id}/tracking`

```bash
curl -X PUT http://localhost:8080/api/orders/26/tracking \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <admin-token>" \
  -d '{
    "trackingNumber": "TRK123456789",
    "status": "SHIPPED"
  }'
```

### 4.5 Get All Orders (Admin Only)
**GET** `/orders/all`

```bash
curl -X GET http://localhost:8080/api/orders/all \
  -H "Authorization: Bearer <admin-token>"
```

---

## 5. User Endpoints

### 5.1 Get User Profile
**GET** `/users/profile`

```bash
curl -X GET http://localhost:8080/api/users/profile \
  -H "Authorization: Bearer <user-token>"
```

### 5.2 Update User Profile
**PUT** `/users/profile`

```bash
curl -X PUT http://localhost:8080/api/users/profile \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <user-token>" \
  -d '{
    "firstName": "John",
    "lastName": "Smith",
    "phoneNumber": "9876543211",
    "address": "456 New Street"
  }'
```

### 5.3 Get All Users (Admin Only)
**GET** `/users`

```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer <admin-token>"
```

---

## 6. Testing with Postman

### 6.1 Setup Postman Collection

1. Create a new collection called "Vineetha E-commerce API"
2. Set up environment variables:
   - `base_url`: `http://localhost:8080/api`
   - `user_token`: (will be set after login)
   - `admin_token`: (will be set after admin login)

### 6.2 Authentication Flow

1. **Register User**
   - Method: POST
   - URL: `{{base_url}}/auth/register`
   - Body: Raw JSON with user data

2. **Login User**
   - Method: POST
   - URL: `{{base_url}}/auth/login`
   - Body: Raw JSON with credentials
   - Tests tab: Add script to save token
   ```javascript
   if (pm.response.code === 200) {
       const response = pm.response.json();
       pm.environment.set("user_token", response.data.token);
   }
   ```

3. **Login Admin**
   - Method: POST
   - URL: `{{base_url}}/auth/login`
   - Body: Raw JSON with admin credentials
   - Tests tab: Add script to save admin token
   ```javascript
   if (pm.response.code === 200) {
       const response = pm.response.json();
       pm.environment.set("admin_token", response.data.token);
   }
   ```

### 6.3 Product Testing

1. **Get Products**
   - Method: GET
   - URL: `{{base_url}}/products`

2. **Get Products with Filters**
   - Method: GET
   - URL: `{{base_url}}/products?category=ELECTRONICS&page=0&size=5`

3. **Search Products**
   - Method: GET
   - URL: `{{base_url}}/products/search?q=iPhone`

### 6.4 Cart Testing

1. **Add to Cart**
   - Method: POST
   - URL: `{{base_url}}/cart/add`
   - Headers: `Authorization: Bearer {{user_token}}`
   - Body: Raw JSON with productId and quantity

2. **Get Cart**
   - Method: GET
   - URL: `{{base_url}}/cart`
   - Headers: `Authorization: Bearer {{user_token}}`

### 6.5 Order Testing

1. **Create Order**
   - Method: POST
   - URL: `{{base_url}}/orders`
   - Headers: `Authorization: Bearer {{user_token}}`
   - Body: Raw JSON with order data

2. **Get Orders**
   - Method: GET
   - URL: `{{base_url}}/orders`
   - Headers: `Authorization: Bearer {{user_token}}`

---

## 7. Error Handling Examples

### 7.1 Unauthorized Access
```bash
curl -X GET http://localhost:8080/api/cart
```
**Response:**
```json
{
  "success": false,
  "message": "Access denied",
  "error": "Unauthorized"
}
```

### 7.2 Invalid Product ID
```bash
curl -X GET http://localhost:8080/api/products/999
```
**Response:**
```json
{
  "success": false,
  "message": "Product not found",
  "error": "Product with ID 999 not found"
}
```

### 7.3 Insufficient Stock
```bash
curl -X POST http://localhost:8080/api/cart/add \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <user-token>" \
  -d '{
    "productId": 1,
    "quantity": 1000
  }'
```
**Response:**
```json
{
  "success": false,
  "message": "Insufficient stock",
  "error": "Requested quantity exceeds available stock"
}
```

---

## 8. Performance Testing

### 8.1 Load Testing with Apache Bench

```bash
# Test product listing endpoint
ab -n 1000 -c 10 http://localhost:8080/api/products

# Test with authentication
ab -n 100 -c 5 -H "Authorization: Bearer <token>" http://localhost:8080/api/cart
```

### 8.2 Database Connection Test

```bash
# Test health endpoint
curl -X GET http://localhost:8080/api/health
```

---

## 9. Common Issues and Solutions

### 9.1 CORS Issues
If you encounter CORS errors when testing from a browser:
- The backend is configured to allow requests from `http://localhost:3000`, `http://localhost:8080`, and `http://127.0.0.1:5500`
- For other origins, update the CORS configuration in `SecurityConfig.java`

### 9.2 Token Expiration
JWT tokens expire after 24 hours. If you get "Token expired" errors:
- Re-login to get a new token
- Update the token in your testing environment

### 9.3 Database Connection
If you get database connection errors:
- Ensure MySQL is running
- Check database credentials in `application.properties`
- Verify the database and tables exist

### 9.4 Port Conflicts
If port 8080 is already in use:
- Change the port in `application.properties`: `server.port=8081`
- Update the base URL in your tests accordingly

---

## 10. Sample Test Data

### 10.1 Sample Users
```json
{
  "customer": {
    "email": "customer@example.com",
    "password": "password123"
  },
  "admin": {
    "email": "admin@vineetha.in",
    "password": "password"
  }
}
```

### 10.2 Sample Products
- Product ID 1: iPhone 14 Pro (Electronics)
- Product ID 2: Nike Air Max 270 (Clothing)
- Product ID 3: Sony WH-1000XM4 (Electronics)
- Product ID 4: MacBook Pro M2 (Electronics)
- Product ID 5: Adidas Ultraboost (Clothing)

### 10.3 Sample Orders
- Order ID 1: Customer 1, iPhone 14 Pro
- Order ID 2: Customer 2, Nike Air Max 270
- Order ID 3: Customer 3, Sony Headphones

---

## 11. Automated Testing Script

Create a shell script for automated testing:

```bash
#!/bin/bash

BASE_URL="http://localhost:8080/api"

echo "Starting API Tests..."

# Test health endpoint
echo "Testing health endpoint..."
curl -s -o /dev/null -w "%{http_code}" $BASE_URL/health

# Test product listing
echo "Testing product listing..."
curl -s -o /dev/null -w "%{http_code}" $BASE_URL/products

# Test registration
echo "Testing user registration..."
REGISTER_RESPONSE=$(curl -s -X POST $BASE_URL/auth/register \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Test","lastName":"User","email":"test@example.com","password":"password123","phoneNumber":"9876543210"}')

echo "Registration response: $REGISTER_RESPONSE"

echo "API Tests completed!"
```

Make it executable and run:
```bash
chmod +x test_api.sh
./test_api.sh
```

This comprehensive testing guide will help you verify all API endpoints and ensure the backend is working correctly before integrating with the frontend. 