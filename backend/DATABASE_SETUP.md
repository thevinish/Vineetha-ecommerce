# Database Setup Guide for Vineetha E-commerce

## Prerequisites
- MySQL 8.0 or higher installed
- MySQL server running on localhost:3306
- MySQL command line client or MySQL Workbench

## Step 1: Install MySQL (if not already installed)

### On macOS:
```bash
# Using Homebrew
brew install mysql
brew services start mysql

# Or download from MySQL official website
# https://dev.mysql.com/downloads/mysql/
```

### On Windows:
- Download MySQL Installer from https://dev.mysql.com/downloads/installer/
- Run the installer and follow the setup wizard

### On Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
sudo systemctl enable mysql
```

## Step 2: Access MySQL

```bash
# Connect to MySQL as root
mysql -u root -p

# Enter your root password when prompted
```

## Step 3: Run Database Initialization Script

### Option 1: Using MySQL Command Line
```bash
# Navigate to the backend directory
cd backend

# Run the initialization script
mysql -u root -p < database/init.sql
```

### Option 2: Using MySQL Workbench
1. Open MySQL Workbench
2. Connect to your MySQL server
3. Open the `database/init.sql` file
4. Execute the script

### Option 3: Copy and Paste
1. Open MySQL command line or Workbench
2. Copy the contents of `database/init.sql`
3. Paste and execute

## Step 4: Verify Database Setup

```sql
-- Connect to the database
USE vineetha_db;

-- Check if tables were created
SHOW TABLES;

-- Verify sample data
SELECT COUNT(*) as user_count FROM users;
SELECT COUNT(*) as product_count FROM products;
SELECT COUNT(*) as order_count FROM orders;
```

Expected output:
```
+------------------+
| Tables_in_vineetha_db |
+------------------+
| cart_items       |
| order_items      |
| orders           |
| product_images   |
| products         |
| reviews          |
| users            |
+------------------+

+------------+
| user_count |
+------------+
|         31 |  -- 30 sample users + 1 admin
+------------+

+----------------+
| product_count  |
+----------------+
|         50     |
+----------------+

+-------------+
| order_count |
+-------------+
|         25  |
+-------------+
```

## Step 5: Update Application Properties (if needed)

The application is configured to use these default settings:
- Database: `vineetha_db`
- Username: `root`
- Password: `vinish@2008`
- Host: `localhost`
- Port: `3306`

If your MySQL setup uses different credentials, update `src/main/resources/application.properties`:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Step 6: Test Database Connection

Start the Spring Boot application:
```bash
cd backend
mvn spring-boot:run
```

Check the logs for successful database connection:
```
Hibernate: create table users...
Hibernate: create table products...
...
Started VinettaApplication in X.XXX seconds
```

## Troubleshooting

### Common Issues:

1. **Access Denied Error**
   ```bash
   # Reset MySQL root password
   sudo mysql
   ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'new_password';
   FLUSH PRIVILEGES;
   ```

2. **Database Already Exists**
   ```sql
   DROP DATABASE IF EXISTS vineetha_db;
   CREATE DATABASE vineetha_db;
   ```

3. **Port Already in Use**
   ```bash
   # Check what's using port 3306
   sudo lsof -i :3306
   
   # Kill the process or change MySQL port
   ```

4. **Connection Timeout**
   - Check if MySQL service is running
   - Verify firewall settings
   - Check MySQL configuration file

### Verification Commands:

```sql
-- Check database exists
SHOW DATABASES;

-- Check user privileges
SHOW GRANTS FOR 'vineetha_user'@'localhost';

-- Check table structure
DESCRIBE users;
DESCRIBE products;
DESCRIBE orders;

-- Check sample data
SELECT * FROM users LIMIT 5;
SELECT * FROM products LIMIT 5;
SELECT * FROM orders LIMIT 5;
```

## Sample Admin Credentials

After setup, you can login with:
- **Email**: admin@vineetha.in
- **Password**: password (encrypted in database)

## Next Steps

Once the database is set up:
1. Start the Spring Boot application
2. Test the API endpoints
3. Integrate with the frontend
4. Add more sample data as needed

## Database Schema Overview

The database includes these main tables:
- **users**: Customer and admin user accounts
- **products**: Product catalog with categories
- **orders**: Order information and status
- **order_items**: Individual items in orders
- **cart_items**: Shopping cart contents
- **reviews**: Product reviews and ratings
- **product_images**: Product image URLs

All tables include proper foreign key relationships and indexes for optimal performance. 