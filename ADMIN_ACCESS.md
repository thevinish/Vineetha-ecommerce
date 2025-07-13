# 🛡️ Vineetha E-commerce - Admin Access Guide

## 🔐 Admin Credentials

### Backend Admin Access
- **Email:** `admin@vineetha.in`
- **Password:** `admin123`
- **Role:** `ADMIN`
- **Status:** `ACTIVE`

### Database Access
- **Database Name:** `vineetha_db`
- **Username:** `root`
- **Password:** `vinish@2008`
- **Host:** `localhost`
- **Port:** `3306`

## 🗄️ Database Connection Commands

### Connect to MySQL
```bash
mysql -u root -pvinish@2008
```

### Use Database
```sql
USE vineetha_db;
```

### View All Tables
```sql
SHOW TABLES;
```

### Check Database Statistics
```sql
SELECT 'Users' as table_name, COUNT(*) as count FROM users
UNION ALL
SELECT 'Products', COUNT(*) FROM products
UNION ALL
SELECT 'Orders', COUNT(*) FROM orders
UNION ALL
SELECT 'Order Items', COUNT(*) FROM order_items
UNION ALL
SELECT 'Cart Items', COUNT(*) FROM cart_items
UNION ALL
SELECT 'Reviews', COUNT(*) FROM reviews;
```

## 🚀 Quick Start Commands

### Start Backend Server
```bash
cd backend
mvn spring-boot:run
```

### Access Backend API
- **Base URL:** `http://localhost:8080`
- **Health Check:** `http://localhost:8080/api/health`
- **Admin Login:** `POST http://localhost:8080/api/auth/login`

### Frontend Access
- **Main Site:** `http://localhost:3000` (if using live server)
- **Admin Panel:** `http://localhost:3000/admin.html`

## 📊 Current Database Status

### Users: 31
- 1 Admin user
- 30 Customer users

### Products: 10
- Electronics: iPhone 14 Pro, MacBook Pro M2, Sony WH-1000XM4, etc.
- Clothing: Nike Air Max 270, Adidas Ultraboost, etc.

### Orders: 2
- Sample orders with order items

### Cart Items: 0
- Ready for user shopping

### Reviews: 0
- Ready for user reviews

## 🔧 Important API Endpoints

### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout

### Products
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create product (Admin only)
- `PUT /api/products/{id}` - Update product (Admin only)
- `DELETE /api/products/{id}` - Delete product (Admin only)

### Cart
- `GET /api/cart` - Get user cart
- `POST /api/cart/add` - Add item to cart
- `PUT /api/cart/update` - Update cart item
- `DELETE /api/cart/remove/{itemId}` - Remove item from cart

### Orders
- `GET /api/orders` - Get user orders
- `POST /api/orders` - Create order
- `GET /api/orders/{id}` - Get order by ID
- `PUT /api/orders/{id}/status` - Update order status (Admin only)

### Users
- `GET /api/users` - Get all users (Admin only)
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user (Admin only)

## 🛠️ Admin Operations

### Add New Product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "New Product",
    "description": "Product description",
    "brand": "Brand Name",
    "price": 9999.00,
    "originalPrice": 11999.00,
    "stockQuantity": 50,
    "category": "ELECTRONICS",
    "mainImage": "assets/images/products/product.jpg",
    "isFeatured": false
  }'
```

### Update Order Status
```bash
curl -X PUT http://localhost:8080/api/orders/1/status \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "status": "SHIPPED",
    "trackingNumber": "TRK123456789"
  }'
```

### Get All Users
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🔍 Database Queries

### View Admin User
```sql
SELECT * FROM users WHERE role = 'ADMIN';
```

### View All Products
```sql
SELECT id, name, brand, price, category, status FROM products;
```

### View Recent Orders
```sql
SELECT o.order_number, u.email, o.total_amount, o.status, o.created_at 
FROM orders o 
JOIN users u ON o.user_id = u.id 
ORDER BY o.created_at DESC;
```

### View Cart Items
```sql
SELECT ci.id, u.email, p.name, ci.quantity, ci.unit_price 
FROM cart_items ci 
JOIN users u ON ci.user_id = u.id 
JOIN products p ON ci.product_id = p.id;
```

## 🚨 Troubleshooting

### Database Connection Issues
1. Check if MySQL is running: `brew services list | grep mysql`
2. Start MySQL: `brew services start mysql`
3. Verify credentials: `mysql -u root -pvinish@2008 -e "SELECT 1;"`

### Backend Issues
1. Check if port 8080 is free: `lsof -i :8080`
2. Kill process if needed: `kill -9 PID`
3. Rebuild project: `cd backend && mvn clean install`

### JWT Token Issues
1. Check token expiration
2. Verify token format
3. Regenerate token by logging in again

## 📝 Notes

- **JWT Secret:** Configured in `application.properties`
- **Password Encryption:** BCrypt with salt rounds 10
- **Session Timeout:** 24 hours
- **File Upload:** Supported for product images
- **Email Verification:** Implemented but not enforced

## 🔗 Useful Links

- **Project Summary:** `PROJECT_SUMMARY.md`
- **API Testing Guide:** `API_TESTING_GUIDE.md`
- **Database Setup:** `backend/DATABASE_SETUP.md`
- **Quick Start Script:** `quick-start.sh`

---

**⚠️ Security Note:** Keep this file secure and don't commit it to version control with real credentials in production environments. 