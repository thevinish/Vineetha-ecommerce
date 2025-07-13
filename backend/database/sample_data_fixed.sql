-- Fixed Sample Data for Vineetha E-commerce
-- This script adds more sample data to the existing database

USE vineetha_db;

-- Add more users (30 additional users)
INSERT INTO users (first_name, last_name, email, password, role, status, email_verified, phone_number, address, city, state, postal_code, country) VALUES
('Aarav', 'Sharma', 'aarav.sharma1@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'CUSTOMER', 'ACTIVE', true, '9876543210', '123 Main St', 'Mumbai', 'Maharashtra', '400001', 'India'),
('Vivaan', 'Patel', 'vivaan.patel2@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'CUSTOMER', 'ACTIVE', true, '9876543211', '456 Park Ave', 'Ahmedabad', 'Gujarat', '380001', 'India'),
('Aditya', 'Reddy', 'aditya.reddy3@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'CUSTOMER', 'ACTIVE', true, '9876543212', '789 Lake Rd', 'Hyderabad', 'Telangana', '500001', 'India'),
('Vihaan', 'Gupta', 'vihaan.gupta4@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'CUSTOMER', 'ACTIVE', true, '9876543213', '321 Hill St', 'Delhi', 'Delhi', '110001', 'India'),
('Arjun', 'Kumar', 'arjun.kumar5@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'CUSTOMER', 'ACTIVE', true, '9876543214', '654 River Rd', 'Chennai', 'Tamil Nadu', '600001', 'India'),
('Sai', 'Singh', 'sai.singh6@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'CUSTOMER', 'ACTIVE', true, '9876543215', '987 Ocean Dr', 'Kolkata', 'West Bengal', '700001', 'India'),
('Reyansh', 'Jain', 'reyansh.jain7@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'CUSTOMER', 'ACTIVE', true, '9876543216', '159 Forest Ln', 'Pune', 'Maharashtra', '411001', 'India'),
('Ayaan', 'Agarwal', 'ayaan.agarwal8@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'CUSTOMER', 'ACTIVE', true, '9876543217', '753 Valley Rd', 'Jaipur', 'Rajasthan', '302001', 'India'),
('Krishna', 'Mehta', 'krishna.mehta9@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'CUSTOMER', 'ACTIVE', true, '9876543218', '852 Ridge St', 'Lucknow', 'Uttar Pradesh', '226001', 'India'),
('Ishaan', 'Chopra', 'ishaan.chopra10@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'CUSTOMER', 'ACTIVE', true, '9876543219', '951 Bay Ave', 'Bhopal', 'Madhya Pradesh', '462001', 'India');

-- Add more products (45 additional products)
INSERT INTO products (name, description, brand, price, original_price, stock_quantity, category, status, main_image, is_featured) VALUES
-- Electronics
('Samsung Galaxy S23', 'Flagship Android smartphone with high-res display', 'Samsung', 79999.00, 89999.00, 60, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/samsung.jpg', false),
('OnePlus Nord', 'Mid-range Android smartphone', 'OnePlus', 29999.00, 34999.00, 60, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/iphone.jpg', false),
('Apple Watch Series 8', 'Latest smartwatch from Apple', 'Apple', 39999.00, 44999.00, 30, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/watch.jpg', true),
('Dell XPS 13', 'Ultra-portable laptop with InfinityEdge display', 'Dell', 99999.00, 109999.00, 40, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/laptop.jpg', false),
('Asus ROG Laptop', 'Gaming laptop with high-end specs', 'Asus', 129999.00, 139999.00, 15, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/laptop.jpg', false),
('iPad Air', 'Lightweight tablet for productivity', 'Apple', 59999.00, 64999.00, 35, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/ipad.jpg', false),
('Bose SoundLink Speaker', 'Portable Bluetooth speaker', 'Bose', 12999.00, 14999.00, 45, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/headphones.jpg', false),
('Samsung Galaxy Buds', 'Wireless earbuds with noise cancellation', 'Samsung', 9999.00, 11999.00, 50, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/headphones.jpg', false),
('Boat Rockerz Headphones', 'Wireless headphones with deep bass', 'Boat', 1999.00, 2499.00, 80, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/headphones.jpg', false),
('HP DeskJet Printer', 'All-in-one color printer', 'HP', 5999.00, 6999.00, 25, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),

-- Fashion
('Levi''s 501 Jeans', 'Classic straight fit jeans', 'Levi''s', 2999.00, 3999.00, 120, 'CLOTHING', 'ACTIVE', 'assets/images/products/fashion.jpg', false),
('Puma Sports T-shirt', 'Breathable sports t-shirt for men', 'Puma', 999.00, 1299.00, 200, 'CLOTHING', 'ACTIVE', 'assets/images/products/fashion.jpg', false),
('Allen Solly Shirt', 'Formal shirt for men', 'Allen Solly', 1999.00, 2499.00, 110, 'CLOTHING', 'ACTIVE', 'assets/images/products/fashion.jpg', false),
('Pepe Jeans Dress', 'Trendy dress for women', 'Pepe Jeans', 2999.00, 3499.00, 70, 'CLOTHING', 'ACTIVE', 'assets/images/products/fashion.jpg', false),
('Zara Blouse', 'Elegant blouse for women', 'Zara', 2499.00, 2999.00, 85, 'CLOTHING', 'ACTIVE', 'assets/images/products/fashion.jpg', false),
('Adidas Ultraboost', 'High-performance running shoes', 'Adidas', 12999.00, 14999.00, 75, 'CLOTHING', 'ACTIVE', 'assets/images/products/adidas.jpg', false),
('Reebok Running Shoes', 'Lightweight shoes for runners', 'Reebok', 4999.00, 5999.00, 90, 'CLOTHING', 'ACTIVE', 'assets/images/products/nike-shoes.jpg', false),
('Sparx Sandals', 'Comfortable sandals for men', 'Sparx', 799.00, 999.00, 100, 'CLOTHING', 'ACTIVE', 'assets/images/products/nike-shoes.jpg', false),
('Fossil Gen 5 Smartwatch', 'Stylish smartwatch with fitness tracking', 'Fossil', 22999.00, 24999.00, 35, 'CLOTHING', 'ACTIVE', 'assets/images/products/watch.jpg', false),
('Casio G-Shock', 'Durable digital watch', 'Casio', 3999.00, 4999.00, 60, 'CLOTHING', 'ACTIVE', 'assets/images/products/watch.jpg', false),

-- Home & Kitchen
('Philips Mixer Grinder', 'Powerful kitchen appliance', 'Philips', 3499.00, 3999.00, 35, 'HOME_GARDEN', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),
('Samsung Microwave Oven', 'Convection microwave for home', 'Samsung', 8999.00, 9999.00, 30, 'HOME_GARDEN', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),
('Bosch Drill Machine', 'Powerful drill for home improvement', 'Bosch', 3999.00, 4499.00, 25, 'HOME_GARDEN', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),
('IKEA Study Table', 'Modern study table for home', 'IKEA', 5999.00, 6999.00, 20, 'HOME_GARDEN', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),
('Godrej Wardrobe', 'Spacious wardrobe for bedroom', 'Godrej', 15999.00, 17999.00, 15, 'HOME_GARDEN', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),

-- Beauty & Health
('Nivea Face Wash', 'Gentle face wash for all skin types', 'Nivea', 199.00, 299.00, 150, 'BEAUTY_HEALTH', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),
('Lakme Face Cream', 'Moisturizing face cream', 'Lakme', 299.00, 399.00, 120, 'BEAUTY_HEALTH', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),
('Himalaya Shampoo', 'Herbal shampoo for hair care', 'Himalaya', 299.00, 399.00, 120, 'BEAUTY_HEALTH', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),
('Dove Hair Conditioner', 'Nourishing hair conditioner', 'Dove', 199.00, 299.00, 100, 'BEAUTY_HEALTH', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),
('Philips Trimmer', 'Beard trimmer for men', 'Philips', 1299.00, 1499.00, 90, 'BEAUTY_HEALTH', 'ACTIVE', 'assets/images/products/kitchen.jpg', false),

-- Books
('The Alchemist', 'Bestselling novel by Paulo Coelho', 'HarperCollins', 399.00, 499.00, 80, 'BOOKS', 'ACTIVE', 'assets/images/products/books.jpg', false),
('Atomic Habits', 'Self-improvement book by James Clear', 'Penguin', 499.00, 599.00, 90, 'BOOKS', 'ACTIVE', 'assets/images/products/books.jpg', false),
('The Power of Habit', 'Book by Charles Duhigg', 'Random House', 499.00, 599.00, 60, 'BOOKS', 'ACTIVE', 'assets/images/products/books.jpg', false),
('Rich Dad Poor Dad', 'Book by Robert Kiyosaki', 'Plata Publishing', 399.00, 499.00, 70, 'BOOKS', 'ACTIVE', 'assets/images/products/books.jpg', false),
('Classmate Notebook', 'Spiral bound notebook', 'Classmate', 50.00, 75.00, 300, 'BOOKS', 'ACTIVE', 'assets/images/products/books.jpg', false),

-- Toys & Games
('Funskool Board Game', 'Family board game for all ages', 'Funskool', 1499.00, 1799.00, 80, 'TOYS_GAMES', 'ACTIVE', 'assets/images/products/gaming.jpg', false),
('Lego Classic Set', 'Creative building blocks for kids', 'Lego', 2499.00, 2999.00, 70, 'TOYS_GAMES', 'ACTIVE', 'assets/images/products/gaming.jpg', false),
('Yamaha Guitar', 'Acoustic guitar for beginners', 'Yamaha', 8999.00, 9999.00, 25, 'TOYS_GAMES', 'ACTIVE', 'assets/images/products/gaming.jpg', false),
('Casio Keyboard', 'Electronic keyboard for music', 'Casio', 12999.00, 14999.00, 20, 'TOYS_GAMES', 'ACTIVE', 'assets/images/products/gaming.jpg', false),

-- Sports & Outdoors
('Yonex Badminton Racket', 'Lightweight racket for professionals', 'Yonex', 2999.00, 3499.00, 60, 'SPORTS_OUTDOORS', 'ACTIVE', 'assets/images/products/basketball.jpg', false),
('Safari Suitcase', 'Large suitcase for travel', 'Safari', 4999.00, 5999.00, 30, 'SPORTS_OUTDOORS', 'ACTIVE', 'assets/images/products/basketball.jpg', false),

-- Automotive
('Castrol Engine Oil', 'Premium engine oil for cars', 'Castrol', 999.00, 1199.00, 100, 'AUTOMOTIVE', 'ACTIVE', 'assets/images/products/car.jpg', false),
('Vega Helmet', 'Safety helmet for bikes', 'Vega', 1499.00, 1799.00, 80, 'AUTOMOTIVE', 'ACTIVE', 'assets/images/products/car.jpg', false),

-- Gaming
('Sony PlayStation 5', 'Next-gen gaming console', 'Sony', 49999.00, 54999.00, 10, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/gaming.jpg', true),
('LG 4K OLED TV', 'Stunning 4K OLED display for home entertainment', 'LG', 129999.00, 139999.00, 15, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/tv.jpg', false),
('Mi Smart LED TV', 'Affordable smart TV with HD display', 'Xiaomi', 29999.00, 32999.00, 40, 'ELECTRONICS', 'ACTIVE', 'assets/images/products/tv.jpg', false);

-- Add some sample orders
INSERT INTO orders (order_number, user_id, total_amount, subtotal, tax_amount, shipping_amount, discount_amount, status, payment_status, payment_method, shipping_address, billing_address, shipping_city, shipping_state, shipping_postal_code, shipping_country, tracking_number, estimated_delivery, delivered_at, notes, promo_code) VALUES
('ORD-2024-001', 2, 92998.00, 89999.00, 4500.00, 0.00, 1500.00, 'DELIVERED', 'PAID', 'CREDIT_CARD', '123 Main St, Mumbai, Maharashtra 400001', '123 Main St, Mumbai, Maharashtra 400001', 'Mumbai', 'Maharashtra', '400001', 'India', 'TRK123456789', '2024-01-15 14:00:00', '2024-01-14 16:30:00', 'Handle with care', 'WELCOME10'),
('ORD-2024-002', 3, 9499.00, 8999.00, 450.00, 0.00, 50.00, 'DELIVERED', 'PAID', 'UPI', '456 Park Ave, Ahmedabad, Gujarat 380001', '456 Park Ave, Ahmedabad, Gujarat 380001', 'Ahmedabad', 'Gujarat', '380001', 'India', 'TRK123456790', '2024-01-16 14:00:00', '2024-01-15 11:20:00', 'Leave at doorstep', NULL),
('ORD-2024-003', 4, 27499.00, 24999.00, 2250.00, 0.00, 250.00, 'SHIPPED', 'PAID', 'DEBIT_CARD', '789 Lake Rd, Hyderabad, Telangana 500001', '789 Lake Rd, Hyderabad, Telangana 500001', 'Hyderabad', 'Telangana', '500001', 'India', 'TRK123456791', '2024-01-18 14:00:00', NULL, 'Call before delivery', 'AUDIO20'),
('ORD-2024-004', 5, 154999.00, 149999.00, 7500.00, 0.00, 500.00, 'PROCESSING', 'PAID', 'NET_BANKING', '321 Hill St, Delhi, Delhi 110001', '321 Hill St, Delhi, Delhi 110001', 'Delhi', 'Delhi', '110001', 'India', 'TRK123456792', '2024-01-20 14:00:00', NULL, 'Signature required', 'LAPTOP50'),
('ORD-2024-005', 6, 13499.00, 12999.00, 675.00, 0.00, 175.00, 'CONFIRMED', 'PAID', 'WALLET', '654 River Rd, Chennai, Tamil Nadu 600001', '654 River Rd, Chennai, Tamil Nadu 600001', 'Chennai', 'Tamil Nadu', '600001', 'India', 'TRK123456793', '2024-01-22 14:00:00', NULL, 'Handle with care', 'SHOES15');

-- Add order items
INSERT INTO order_items (order_id, product_id, quantity, unit_price, total_price, discount_amount) VALUES
(1, 1, 1, 89999.00, 89999.00, 1500.00),
(2, 2, 1, 8999.00, 8999.00, 50.00),
(3, 3, 1, 24999.00, 24999.00, 250.00),
(4, 4, 1, 149999.00, 149999.00, 500.00),
(5, 5, 1, 12999.00, 12999.00, 175.00);

-- Show final statistics
SELECT 'Final Database Statistics' as info;
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