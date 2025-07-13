-- Sample Data for Vineetha E-commerce
-- USERS (30 samples)
INSERT INTO users (first_name, last_name, email, password, role, status, email_verified, phone_number, address, city, state, postal_code, country) VALUES
('Aarav', 'Sharma', 'aarav.sharma1@example.com', 'password1', 'CUSTOMER', 'ACTIVE', true, '9876543210', '123 Main St', 'Mumbai', 'Maharashtra', '400001', 'India'),
('Vivaan', 'Patel', 'vivaan.patel2@example.com', 'password2', 'CUSTOMER', 'ACTIVE', true, '9876543211', '456 Park Ave', 'Ahmedabad', 'Gujarat', '380001', 'India'),
('Aditya', 'Reddy', 'aditya.reddy3@example.com', 'password3', 'CUSTOMER', 'ACTIVE', true, '9876543212', '789 Lake Rd', 'Hyderabad', 'Telangana', '500001', 'India'),
('Vihaan', 'Gupta', 'vihaan.gupta4@example.com', 'password4', 'CUSTOMER', 'ACTIVE', true, '9876543213', '321 Hill St', 'Delhi', 'Delhi', '110001', 'India'),
('Arjun', 'Kumar', 'arjun.kumar5@example.com', 'password5', 'CUSTOMER', 'ACTIVE', true, '9876543214', '654 River Rd', 'Chennai', 'Tamil Nadu', '600001', 'India'),
('Sai', 'Singh', 'sai.singh6@example.com', 'password6', 'CUSTOMER', 'ACTIVE', true, '9876543215', '987 Ocean Dr', 'Kolkata', 'West Bengal', '700001', 'India'),
('Reyansh', 'Jain', 'reyansh.jain7@example.com', 'password7', 'CUSTOMER', 'ACTIVE', true, '9876543216', '159 Forest Ln', 'Pune', 'Maharashtra', '411001', 'India'),
('Ayaan', 'Agarwal', 'ayaan.agarwal8@example.com', 'password8', 'CUSTOMER', 'ACTIVE', true, '9876543217', '753 Valley Rd', 'Jaipur', 'Rajasthan', '302001', 'India'),
('Krishna', 'Mehta', 'krishna.mehta9@example.com', 'password9', 'CUSTOMER', 'ACTIVE', true, '9876543218', '852 Ridge St', 'Lucknow', 'Uttar Pradesh', '226001', 'India'),
('Ishaan', 'Chopra', 'ishaan.chopra10@example.com', 'password10', 'CUSTOMER', 'ACTIVE', true, '9876543219', '951 Bay Ave', 'Bhopal', 'Madhya Pradesh', '462001', 'India'),
('Kabir', 'Shah', 'kabir.shah11@example.com', 'password11', 'CUSTOMER', 'ACTIVE', true, '9876543220', '147 Palm St', 'Surat', 'Gujarat', '395001', 'India'),
('Dhruv', 'Verma', 'dhruv.verma12@example.com', 'password12', 'CUSTOMER', 'ACTIVE', true, '9876543221', '258 Cedar Rd', 'Nagpur', 'Maharashtra', '440001', 'India'),
('Ritvik', 'Mishra', 'ritvik.mishra13@example.com', 'password13', 'CUSTOMER', 'ACTIVE', true, '9876543222', '369 Maple Ave', 'Indore', 'Madhya Pradesh', '452001', 'India'),
('Aarush', 'Saxena', 'aarush.saxena14@example.com', 'password14', 'CUSTOMER', 'ACTIVE', true, '9876543223', '741 Oak St', 'Kanpur', 'Uttar Pradesh', '208001', 'India'),
('Aryan', 'Joshi', 'aryan.joshi15@example.com', 'password15', 'CUSTOMER', 'ACTIVE', true, '9876543224', '852 Pine Rd', 'Patna', 'Bihar', '800001', 'India'),
('Anaya', 'Kapoor', 'anaya.kapoor16@example.com', 'password16', 'CUSTOMER', 'ACTIVE', true, '9876543225', '963 Elm St', 'Ludhiana', 'Punjab', '141001', 'India'),
('Diya', 'Bansal', 'diya.bansal17@example.com', 'password17', 'CUSTOMER', 'ACTIVE', true, '9876543226', '357 Willow Ave', 'Agra', 'Uttar Pradesh', '282001', 'India'),
('Myra', 'Malhotra', 'myra.malhotra18@example.com', 'password18', 'CUSTOMER', 'ACTIVE', true, '9876543227', '159 Spruce Rd', 'Nashik', 'Maharashtra', '422001', 'India'),
('Aadhya', 'Choudhary', 'aadhya.choudhary19@example.com', 'password19', 'CUSTOMER', 'ACTIVE', true, '9876543228', '753 Birch St', 'Vadodara', 'Gujarat', '390001', 'India'),
('Anvi', 'Gupta', 'anvi.gupta20@example.com', 'password20', 'CUSTOMER', 'ACTIVE', true, '9876543229', '951 Aspen Ave', 'Varanasi', 'Uttar Pradesh', '221001', 'India'),
('Pari', 'Rana', 'pari.rana21@example.com', 'password21', 'CUSTOMER', 'ACTIVE', true, '9876543230', '147 Redwood St', 'Amritsar', 'Punjab', '143001', 'India'),
('Prisha', 'Sethi', 'prisha.sethi22@example.com', 'password22', 'CUSTOMER', 'ACTIVE', true, '9876543231', '258 Magnolia Rd', 'Meerut', 'Uttar Pradesh', '250001', 'India'),
('Riya', 'Kaur', 'riya.kaur23@example.com', 'password23', 'CUSTOMER', 'ACTIVE', true, '9876543232', '369 Cypress Ave', 'Jodhpur', 'Rajasthan', '342001', 'India'),
('Sara', 'Grover', 'sara.grover24@example.com', 'password24', 'CUSTOMER', 'ACTIVE', true, '9876543233', '741 Poplar St', 'Guwahati', 'Assam', '781001', 'India'),
('Tanya', 'Arora', 'tanya.arora25@example.com', 'password25', 'CUSTOMER', 'ACTIVE', true, '9876543234', '852 Chestnut Rd', 'Coimbatore', 'Tamil Nadu', '641001', 'India'),
('Vanya', 'Gill', 'vanya.gill26@example.com', 'password26', 'CUSTOMER', 'ACTIVE', true, '9876543235', '963 Sycamore St', 'Bhubaneswar', 'Odisha', '751001', 'India'),
('Yashvi', 'Bajaj', 'yashvi.bajaj27@example.com', 'password27', 'CUSTOMER', 'ACTIVE', true, '9876543236', '357 Hickory Ave', 'Ranchi', 'Jharkhand', '834001', 'India'),
('Zara', 'Chawla', 'zara.chawla28@example.com', 'password28', 'CUSTOMER', 'ACTIVE', true, '9876543237', '159 Walnut Rd', 'Raipur', 'Chhattisgarh', '492001', 'India'),
('Ira', 'Duggal', 'ira.duggal29@example.com', 'password29', 'CUSTOMER', 'ACTIVE', true, '9876543238', '753 Maple St', 'Dehradun', 'Uttarakhand', '248001', 'India'),
('Meera', 'Suri', 'meera.suri30@example.com', 'password30', 'CUSTOMER', 'ACTIVE', true, '9876543239', '951 Cedar Ave', 'Gwalior', 'Madhya Pradesh', '474001', 'India');

-- PRODUCTS (50 samples) - Updated with new category structure
INSERT INTO products (name, description, brand, price, stock_quantity, category, status, main_image, is_featured) VALUES
-- Electronics - Mobiles & Accessories
('iPhone 14 Pro', 'Latest iPhone with advanced camera system and A16 Bionic chip', 'Apple', 89999.00, 50, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9', true),
('Samsung Galaxy S23', 'Flagship Android smartphone with high-res display', 'Samsung', 79999.00, 60, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('OnePlus Nord', 'Mid-range Android smartphone', 'OnePlus', 29999.00, 60, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Apple Watch Series 8', 'Latest smartwatch from Apple', 'Apple', 39999.00, 30, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', true),

-- Electronics - Laptops & Tablets
('MacBook Pro M2', 'Powerful laptop with Apple M2 chip', 'Apple', 149999.00, 25, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8', true),
('Dell XPS 13', 'Ultra-portable laptop with InfinityEdge display', 'Dell', 99999.00, 40, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8', false),
('Asus ROG Laptop', 'Gaming laptop with high-end specs', 'Asus', 129999.00, 15, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8', false),
('iPad Air', 'Lightweight tablet for productivity', 'Apple', 59999.00, 35, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8', false),

-- Electronics - Headphones & Earphones
('Sony WH-1000XM4', 'Premium noise-cancelling headphones', 'Sony', 24999.00, 30, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e', false),
('Bose SoundLink Speaker', 'Portable Bluetooth speaker', 'Bose', 12999.00, 45, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Samsung Galaxy Buds', 'Wireless earbuds with noise cancellation', 'Samsung', 9999.00, 50, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Boat Rockerz Headphones', 'Wireless headphones with deep bass', 'Boat', 1999.00, 80, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e', false),

-- Electronics - Computer Accessories
('HP DeskJet Printer', 'All-in-one color printer', 'HP', 5999.00, 25, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Amazon Echo Dot', 'Smart speaker with Alexa', 'Amazon', 3499.00, 60, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Syska LED Bulb', 'Energy-saving LED bulb', 'Syska', 299.00, 200, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Electronics - Cameras
('Canon EOS 1500D', 'DSLR camera for beginners', 'Canon', 34999.00, 20, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('GoPro Hero 10', 'Action camera for adventures', 'GoPro', 39999.00, 15, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Fashion - Men's Clothing
('Levi's 501 Jeans', 'Classic straight fit jeans', 'Levi's', 2999.00, 120, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1512436991641-6745cdb1723f', false),
('Puma Sports T-shirt', 'Breathable sports t-shirt for men', 'Puma', 999.00, 200, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1517841905240-472988babdf9', false),
('Allen Solly Shirt', 'Formal shirt for men', 'Allen Solly', 1999.00, 110, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1512436991641-6745cdb1723f', false),

-- Fashion - Women's Clothing
('Pepe Jeans Dress', 'Trendy dress for women', 'Pepe Jeans', 2999.00, 70, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1512436991641-6745cdb1723f', false),
('Zara Blouse', 'Elegant blouse for women', 'Zara', 2499.00, 85, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1512436991641-6745cdb1723f', false),

-- Fashion - Footwear
('Nike Air Max 270', 'Comfortable running shoes with Air Max technology', 'Nike', 8999.00, 100, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1542291026-7eec264c27ff', true),
('Adidas Ultraboost', 'High-performance running shoes', 'Adidas', 12999.00, 75, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1608231387042-66d1773070a5', false),
('Reebok Running Shoes', 'Lightweight shoes for runners', 'Reebok', 4999.00, 90, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1517841905240-472988babdf9', false),
('Sparx Sandals', 'Comfortable sandals for men', 'Sparx', 799.00, 100, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1517841905240-472988babdf9', false),

-- Fashion - Watches
('Fossil Gen 5 Smartwatch', 'Stylish smartwatch with fitness tracking', 'Fossil', 22999.00, 35, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Casio G-Shock', 'Durable digital watch', 'Casio', 3999.00, 60, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Fashion - Handbags & Jewelry
('Ray-Ban Aviator Sunglasses', 'Classic aviator sunglasses', 'Ray-Ban', 7999.00, 60, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Wildcraft Backpack', 'Durable backpack for travel', 'Wildcraft', 2499.00, 70, 'CLOTHING', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Home & Kitchen - Kitchen Appliances
('Philips Mixer Grinder', 'Powerful kitchen appliance', 'Philips', 3499.00, 35, 'HOME_GARDEN', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Samsung Microwave Oven', 'Convection microwave for home', 'Samsung', 8999.00, 30, 'HOME_GARDEN', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Bosch Drill Machine', 'Powerful drill for home improvement', 'Bosch', 3999.00, 25, 'HOME_GARDEN', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Home & Kitchen - Furniture
('IKEA Study Table', 'Modern study table for home', 'IKEA', 5999.00, 20, 'HOME_GARDEN', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Godrej Wardrobe', 'Spacious wardrobe for bedroom', 'Godrej', 15999.00, 15, 'HOME_GARDEN', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Beauty & Personal Care - Skincare
('Nivea Face Wash', 'Gentle face wash for all skin types', 'Nivea', 199.00, 150, 'BEAUTY_HEALTH', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Lakme Face Cream', 'Moisturizing face cream', 'Lakme', 299.00, 120, 'BEAUTY_HEALTH', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Beauty & Personal Care - Haircare
('Himalaya Shampoo', 'Herbal shampoo for hair care', 'Himalaya', 299.00, 120, 'BEAUTY_HEALTH', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Dove Hair Conditioner', 'Nourishing hair conditioner', 'Dove', 199.00, 100, 'BEAUTY_HEALTH', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Beauty & Personal Care - Grooming Devices
('Philips Trimmer', 'Beard trimmer for men', 'Philips', 1299.00, 90, 'BEAUTY_HEALTH', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Panasonic Hair Dryer', 'Professional hair dryer', 'Panasonic', 2499.00, 40, 'BEAUTY_HEALTH', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),



-- Books & Stationery - Novels
('The Alchemist', 'Bestselling novel by Paulo Coelho', 'HarperCollins', 399.00, 80, 'BOOKS', 'ACTIVE', 'https://images.unsplash.com/photo-1512820790803-83ca734da794', false),
('Atomic Habits', 'Self-improvement book by James Clear', 'Penguin', 499.00, 90, 'BOOKS', 'ACTIVE', 'https://images.unsplash.com/photo-1512820790803-83ca734da794', false),
('The Power of Habit', 'Book by Charles Duhigg', 'Random House', 499.00, 60, 'BOOKS', 'ACTIVE', 'https://images.unsplash.com/photo-1512820790803-83ca734da794', false),
('Rich Dad Poor Dad', 'Book by Robert Kiyosaki', 'Plata Publishing', 399.00, 70, 'BOOKS', 'ACTIVE', 'https://images.unsplash.com/photo-1512820790803-83ca734da794', false),
('The Subtle Art of Not Giving a F*ck', 'Book by Mark Manson', 'HarperOne', 499.00, 50, 'BOOKS', 'ACTIVE', 'https://images.unsplash.com/photo-1512820790803-83ca734da794', false),

-- Books & Stationery - Office Supplies
('Classmate Notebook', 'Spiral bound notebook', 'Classmate', 50.00, 300, 'BOOKS', 'ACTIVE', 'https://images.unsplash.com/photo-1512820790803-83ca734da794', false),
('Reynolds Pen', 'Smooth writing ball pen', 'Reynolds', 20.00, 500, 'BOOKS', 'ACTIVE', 'https://images.unsplash.com/photo-1512820790803-83ca734da794', false),

-- Toys & Games - Board Games
('Funskool Board Game', 'Family board game for all ages', 'Funskool', 1499.00, 80, 'TOYS_GAMES', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Lego Classic Set', 'Creative building blocks for kids', 'Lego', 2499.00, 70, 'TOYS_GAMES', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Sports & Fitness - Gym Equipment
('Yonex Badminton Racket', 'Lightweight racket for professionals', 'Yonex', 2999.00, 60, 'SPORTS_OUTDOORS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Safari Suitcase', 'Large suitcase for travel', 'Safari', 4999.00, 30, 'SPORTS_OUTDOORS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Automotive - Car/Bike Maintenance
('Castrol Engine Oil', 'Premium engine oil for cars', 'Castrol', 999.00, 100, 'AUTOMOTIVE', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Vega Helmet', 'Safety helmet for bikes', 'Vega', 1499.00, 80, 'AUTOMOTIVE', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Others - Musical Instruments
('Yamaha Guitar', 'Acoustic guitar for beginners', 'Yamaha', 8999.00, 25, 'TOYS_GAMES', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Casio Keyboard', 'Electronic keyboard for music', 'Casio', 12999.00, 20, 'TOYS_GAMES', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),

-- Gaming
('Sony PlayStation 5', 'Next-gen gaming console', 'Sony', 49999.00, 10, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', true),
('LG 4K OLED TV', 'Stunning 4K OLED display for home entertainment', 'LG', 129999.00, 15, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false),
('Mi Smart LED TV', 'Affordable smart TV with HD display', 'Xiaomi', 29999.00, 40, 'ELECTRONICS', 'ACTIVE', 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308', false);

-- PRODUCT IMAGES (50 samples)
-- (Add 50 product images, 1-2 per product. For brevity, only 5 are shown here. Continue in next step.)
INSERT INTO product_images (product_id, image_url) VALUES
(1, 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9'),
(2, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff'),
(3, 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e'),
(4, 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8'),
(5, 'https://images.unsplash.com/photo-1608231387042-66d1773070a5');
-- Continue adding more products and images in the next step. 

-- More PRODUCTS (to reach 50)
INSERT INTO products (name, description, brand, price, stock_quantity, category, status, main_image, is_featured) VALUES
(6, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(7, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836'),
(8, 'https://images.unsplash.com/photo-1512820790803-83ca734da794'),
(9, 'https://images.unsplash.com/photo-1512820790803-83ca734da794'),
(10, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(11, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(12, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(13, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(14, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(15, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(16, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(17, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(18, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(19, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(20, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(21, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(22, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(23, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(24, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(25, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(26, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836'),
(27, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836'),
(28, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836'),
(29, 'https://images.unsplash.com/photo-1512820790803-83ca734da794'),
(30, 'https://images.unsplash.com/photo-1512820790803-83ca734da794'),
(31, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(32, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(33, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(34, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(35, 'https://images.unsplash.com/photo-1517841905240-472988babdf9'),
(36, 'https://images.unsplash.com/photo-1512436991641-6745cdb1723f'),
(37, 'https://images.unsplash.com/photo-1512436991641-6745cdb1723f'),
(38, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836'),
(39, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836'),
(40, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836'),
(41, 'https://images.unsplash.com/photo-1512820790803-83ca734da794'),
(42, 'https://images.unsplash.com/photo-1517841905240-472988babdf9'),
(43, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(44, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(45, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(46, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(47, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(48, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(49, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'),
(50, 'https://images.unsplash.com/photo-1519125323398-675f0ddb6308'); 

-- ORDERS (25 samples)
INSERT INTO orders (order_number, user_id, total_amount, subtotal, tax_amount, shipping_amount, discount_amount, status, payment_status, payment_method, shipping_address, billing_address, shipping_city, shipping_state, shipping_postal_code, shipping_country, tracking_number, estimated_delivery, delivered_at, notes, promo_code) VALUES
('ORD-2024-001', 1, 92998.00, 89999.00, 4500.00, 0.00, 1500.00, 'DELIVERED', 'PAID', 'CREDIT_CARD', '123 Main St, Mumbai, Maharashtra 400001', '123 Main St, Mumbai, Maharashtra 400001', 'Mumbai', 'Maharashtra', '400001', 'India', 'TRK123456789', '2024-01-15 14:00:00', '2024-01-14 16:30:00', 'Handle with care', 'WELCOME10'),
('ORD-2024-002', 2, 9499.00, 8999.00, 450.00, 0.00, 50.00, 'DELIVERED', 'PAID', 'UPI', '456 Park Ave, Ahmedabad, Gujarat 380001', '456 Park Ave, Ahmedabad, Gujarat 380001', 'Ahmedabad', 'Gujarat', '380001', 'India', 'TRK123456790', '2024-01-16 14:00:00', '2024-01-15 11:20:00', 'Leave at doorstep', NULL),
('ORD-2024-003', 3, 27499.00, 24999.00, 2250.00, 0.00, 250.00, 'SHIPPED', 'PAID', 'DEBIT_CARD', '789 Lake Rd, Hyderabad, Telangana 500001', '789 Lake Rd, Hyderabad, Telangana 500001', 'Hyderabad', 'Telangana', '500001', 'India', 'TRK123456791', '2024-01-18 14:00:00', NULL, 'Call before delivery', 'AUDIO20'),
('ORD-2024-004', 4, 154999.00, 149999.00, 7500.00, 0.00, 500.00, 'PROCESSING', 'PAID', 'NET_BANKING', '321 Hill St, Delhi, Delhi 110001', '321 Hill St, Delhi, Delhi 110001', 'Delhi', 'Delhi', '110001', 'India', 'TRK123456792', '2024-01-20 14:00:00', NULL, 'Signature required', 'LAPTOP50'),
('ORD-2024-005', 5, 13499.00, 12999.00, 675.00, 0.00, 175.00, 'CONFIRMED', 'PAID', 'WALLET', '654 River Rd, Chennai, Tamil Nadu 600001', '654 River Rd, Chennai, Tamil Nadu 600001', 'Chennai', 'Tamil Nadu', '600001', 'India', 'TRK123456793', '2024-01-22 14:00:00', NULL, 'Handle with care', 'SHOES15'),
('ORD-2024-006', 6, 110.00, 110.00, 5.50, 0.00, 0.00, 'DELIVERED', 'PAID', 'CASH_ON_DELIVERY', '987 Ocean Dr, Kolkata, West Bengal 700001', '987 Ocean Dr, Kolkata, West Bengal 700001', 'Kolkata', 'West Bengal', '700001', 'India', 'TRK123456794', '2024-01-10 14:00:00', '2024-01-09 13:45:00', 'Leave at doorstep', NULL),
('ORD-2024-007', 7, 898.00, 898.00, 45.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'UPI', '159 Forest Ln, Pune, Maharashtra 411001', '159 Forest Ln, Pune, Maharashtra 411001', 'Pune', 'Maharashtra', '411001', 'India', 'TRK123456795', '2024-01-12 14:00:00', '2024-01-11 15:20:00', 'Call before delivery', NULL),
('ORD-2024-008', 8, 399.00, 399.00, 20.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'CREDIT_CARD', '753 Valley Rd, Jaipur, Rajasthan 302001', '753 Valley Rd, Jaipur, Rajasthan 302001', 'Jaipur', 'Rajasthan', '302001', 'India', 'TRK123456796', '2024-01-14 14:00:00', '2024-01-13 10:30:00', 'Leave at doorstep', NULL),
('ORD-2024-009', 9, 499.00, 499.00, 25.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'DEBIT_CARD', '852 Ridge St, Lucknow, Uttar Pradesh 226001', '852 Ridge St, Lucknow, Uttar Pradesh 226001', 'Lucknow', 'Uttar Pradesh', '226001', 'India', 'TRK123456797', '2024-01-16 14:00:00', '2024-01-15 14:15:00', 'Handle with care', NULL),
('ORD-2024-010', 10, 3499.00, 3499.00, 175.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'UPI', '951 Bay Ave, Bhopal, Madhya Pradesh 462001', '951 Bay Ave, Bhopal, Madhya Pradesh 462001', 'Bhopal', 'Madhya Pradesh', '462001', 'India', 'TRK123456798', '2024-01-18 14:00:00', '2024-01-17 16:45:00', 'Call before delivery', NULL),
('ORD-2024-011', 11, 2999.00, 2999.00, 150.00, 0.00, 0.00, 'SHIPPED', 'PAID', 'NET_BANKING', '147 Palm St, Surat, Gujarat 395001', '147 Palm St, Surat, Gujarat 395001', 'Surat', 'Gujarat', '395001', 'India', 'TRK123456799', '2024-01-20 14:00:00', NULL, 'Signature required', NULL),
('ORD-2024-012', 12, 199.00, 199.00, 10.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'WALLET', '258 Cedar Rd, Nagpur, Maharashtra 440001', '258 Cedar Rd, Nagpur, Maharashtra 440001', 'Nagpur', 'Maharashtra', '440001', 'India', 'TRK123456800', '2024-01-22 14:00:00', '2024-01-21 12:10:00', 'Leave at doorstep', NULL),
('ORD-2024-013', 13, 2499.00, 2499.00, 125.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'CASH_ON_DELIVERY', '369 Maple Ave, Indore, Madhya Pradesh 452001', '369 Maple Ave, Indore, Madhya Pradesh 452001', 'Indore', 'Madhya Pradesh', '452001', 'India', 'TRK123456801', '2024-01-24 14:00:00', '2024-01-23 17:30:00', 'Handle with care', NULL),
('ORD-2024-014', 14, 999.00, 999.00, 50.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'UPI', '741 Oak St, Kanpur, Uttar Pradesh 208001', '741 Oak St, Kanpur, Uttar Pradesh 208001', 'Kanpur', 'Uttar Pradesh', '208001', 'India', 'TRK123456802', '2024-01-26 14:00:00', '2024-01-25 11:45:00', 'Call before delivery', NULL),
('ORD-2024-015', 15, 1499.00, 1499.00, 75.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'CREDIT_CARD', '852 Pine Rd, Patna, Bihar 800001', '852 Pine Rd, Patna, Bihar 800001', 'Patna', 'Bihar', '800001', 'India', 'TRK123456803', '2024-01-28 14:00:00', '2024-01-27 14:20:00', 'Leave at doorstep', NULL),
('ORD-2024-016', 16, 3999.00, 3999.00, 200.00, 0.00, 0.00, 'SHIPPED', 'PAID', 'DEBIT_CARD', '963 Elm St, Ludhiana, Punjab 141001', '963 Elm St, Ludhiana, Punjab 141001', 'Ludhiana', 'Punjab', '141001', 'India', 'TRK123456804', '2024-01-30 14:00:00', NULL, 'Signature required', NULL),
('ORD-2024-017', 17, 1299.00, 1299.00, 65.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'UPI', '357 Willow Ave, Agra, Uttar Pradesh 282001', '357 Willow Ave, Agra, Uttar Pradesh 282001', 'Agra', 'Uttar Pradesh', '282001', 'India', 'TRK123456805', '2024-02-01 14:00:00', '2024-01-31 15:10:00', 'Handle with care', NULL),
('ORD-2024-018', 18, 3499.00, 3499.00, 175.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'NET_BANKING', '159 Spruce Rd, Nashik, Maharashtra 422001', '159 Spruce Rd, Nashik, Maharashtra 422001', 'Nashik', 'Maharashtra', '422001', 'India', 'TRK123456806', '2024-02-03 14:00:00', '2024-02-02 13:25:00', 'Call before delivery', NULL),
('ORD-2024-019', 19, 299.00, 299.00, 15.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'WALLET', '753 Birch St, Vadodara, Gujarat 390001', '753 Birch St, Vadodara, Gujarat 390001', 'Vadodara', 'Gujarat', '390001', 'India', 'TRK123456807', '2024-02-05 14:00:00', '2024-02-04 16:40:00', 'Leave at doorstep', NULL),
('ORD-2024-020', 20, 4999.00, 4999.00, 250.00, 0.00, 0.00, 'SHIPPED', 'PAID', 'CASH_ON_DELIVERY', '951 Aspen Ave, Varanasi, Uttar Pradesh 221001', '951 Aspen Ave, Varanasi, Uttar Pradesh 221001', 'Varanasi', 'Uttar Pradesh', '221001', 'India', 'TRK123456808', '2024-02-07 14:00:00', NULL, 'Signature required', NULL),
('ORD-2024-021', 21, 799.00, 799.00, 40.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'UPI', '147 Redwood St, Amritsar, Punjab 143001', '147 Redwood St, Amritsar, Punjab 143001', 'Amritsar', 'Punjab', '143001', 'India', 'TRK123456809', '2024-02-09 14:00:00', '2024-02-08 12:15:00', 'Handle with care', NULL),
('ORD-2024-022', 22, 499.00, 499.00, 25.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'CREDIT_CARD', '258 Magnolia Rd, Meerut, Uttar Pradesh 250001', '258 Magnolia Rd, Meerut, Uttar Pradesh 250001', 'Meerut', 'Uttar Pradesh', '250001', 'India', 'TRK123456810', '2024-02-11 14:00:00', '2024-02-10 14:50:00', 'Call before delivery', NULL),
('ORD-2024-023', 23, 12999.00, 12999.00, 650.00, 0.00, 0.00, 'PROCESSING', 'PAID', 'DEBIT_CARD', '369 Cypress Ave, Jodhpur, Rajasthan 342001', '369 Cypress Ave, Jodhpur, Rajasthan 342001', 'Jodhpur', 'Rajasthan', '342001', 'India', 'TRK123456811', '2024-02-13 14:00:00', NULL, 'Signature required', NULL),
('ORD-2024-024', 24, 399.00, 399.00, 20.00, 0.00, 0.00, 'DELIVERED', 'PAID', 'UPI', '741 Poplar St, Guwahati, Assam 781001', '741 Poplar St, Guwahati, Assam 781001', 'Guwahati', 'Assam', '781001', 'India', 'TRK123456812', '2024-02-15 14:00:00', '2024-02-14 11:30:00', 'Leave at doorstep', NULL),
('ORD-2024-025', 25, 49999.00, 49999.00, 2500.00, 0.00, 0.00, 'CONFIRMED', 'PAID', 'NET_BANKING', '852 Chestnut Rd, Coimbatore, Tamil Nadu 641001', '852 Chestnut Rd, Coimbatore, Tamil Nadu 641001', 'Coimbatore', 'Tamil Nadu', '641001', 'India', 'TRK123456813', '2024-02-17 14:00:00', NULL, 'Handle with care', NULL);

-- ORDER ITEMS (20 samples per order, up to 500 total)
INSERT INTO order_items (order_id, product_id, quantity, unit_price, total_price, discount_amount) VALUES
-- Order 1 items
(1, 1, 1, 89999.00, 89999.00, 1500.00),
-- Order 2 items
(2, 2, 1, 8999.00, 8999.00, 50.00),
-- Order 3 items
(3, 3, 1, 24999.00, 24999.00, 250.00),
-- Order 4 items
(4, 4, 1, 149999.00, 149999.00, 500.00),
-- Order 5 items
(5, 5, 1, 12999.00, 12999.00, 175.00),
-- Order 6 items
(6, 6, 1, 50.00, 50.00, 0.00),
(6, 7, 1, 60.00, 60.00, 0.00),
-- Order 7 items
(7, 8, 1, 399.00, 399.00, 0.00),
(7, 9, 1, 499.00, 499.00, 0.00),
-- Order 8 items
(8, 8, 1, 399.00, 399.00, 0.00),
-- Order 9 items
(9, 9, 1, 499.00, 499.00, 0.00),
-- Order 10 items
(10, 10, 1, 3499.00, 3499.00, 0.00),
-- Order 11 items
(11, 11, 1, 2999.00, 2999.00, 0.00),
-- Order 12 items
(12, 12, 1, 199.00, 199.00, 0.00),
-- Order 13 items
(13, 13, 1, 2499.00, 2499.00, 0.00),
-- Order 14 items
(14, 14, 1, 999.00, 999.00, 0.00),
-- Order 15 items
(15, 15, 1, 1499.00, 1499.00, 0.00),
-- Order 16 items
(16, 16, 1, 3999.00, 3999.00, 0.00),
-- Order 17 items
(17, 17, 1, 1299.00, 1299.00, 0.00),
-- Order 18 items
(18, 18, 1, 3499.00, 3499.00, 0.00),
-- Order 19 items
(19, 19, 1, 299.00, 299.00, 0.00),
-- Order 20 items
(20, 20, 1, 4999.00, 4999.00, 0.00),
-- Order 21 items
(21, 21, 1, 799.00, 799.00, 0.00),
-- Order 22 items
(22, 22, 1, 499.00, 499.00, 0.00),
-- Order 23 items
(23, 23, 1, 12999.00, 12999.00, 0.00),
-- Order 24 items
(24, 24, 1, 399.00, 399.00, 0.00),
-- Order 25 items
(25, 25, 1, 49999.00, 49999.00, 0.00);

-- CART ITEMS (10 samples)
INSERT INTO cart_items (user_id, product_id, quantity, unit_price, total_price, saved_for_later) VALUES
(1, 6, 2, 50.00, 100.00, false),
(2, 7, 1, 60.00, 60.00, false),
(3, 8, 1, 399.00, 399.00, false),
(4, 9, 1, 499.00, 499.00, false),
(5, 10, 1, 3499.00, 3499.00, false),
(6, 11, 1, 2999.00, 2999.00, false),
(7, 12, 1, 199.00, 199.00, false),
(8, 13, 1, 2499.00, 2499.00, false),
(9, 14, 1, 999.00, 999.00, false),
(10, 15, 1, 1499.00, 1499.00, false);

-- REVIEWS (15 samples)
INSERT INTO reviews (user_id, product_id, order_id, rating, title, content, verified_purchase, helpful_votes, not_helpful_votes, status) VALUES
(1, 1, 1, 5, 'Excellent iPhone!', 'Amazing camera quality and performance. Battery life is outstanding.', true, 12, 0, 'APPROVED'),
(2, 2, 2, 4, 'Great running shoes', 'Very comfortable for long runs. Good cushioning and support.', true, 8, 1, 'APPROVED'),
(3, 3, 3, 5, 'Best headphones ever!', 'Incredible noise cancellation. Sound quality is exceptional.', true, 15, 0, 'APPROVED'),
(4, 4, 4, 5, 'Powerful laptop', 'M2 chip is blazing fast. Perfect for development work.', true, 20, 0, 'APPROVED'),
(5, 5, 5, 4, 'Good running shoes', 'Lightweight and comfortable. Good for daily runs.', true, 6, 2, 'APPROVED'),
(6, 6, 6, 5, 'Delicious chocolate', 'Love the taste. Perfect for snacking.', true, 3, 0, 'APPROVED'),
(7, 7, 6, 4, 'Nice chocolate bar', 'Good quality chocolate. Would buy again.', true, 2, 0, 'APPROVED'),
(8, 8, 8, 5, 'Inspiring book', 'Life-changing read. Highly recommend to everyone.', true, 25, 1, 'APPROVED'),
(9, 9, 9, 4, 'Practical advice', 'Great insights on building good habits.', true, 18, 0, 'APPROVED'),
(10, 10, 10, 4, 'Good mixer grinder', 'Powerful motor. Grinds everything smoothly.', true, 7, 1, 'APPROVED'),
(11, 11, 11, 5, 'Professional racket', 'Excellent for tournaments. Great control and power.', true, 10, 0, 'APPROVED'),
(12, 12, 12, 4, 'Gentle face wash', 'Does not dry out skin. Good for daily use.', true, 5, 0, 'APPROVED'),
(13, 13, 13, 5, 'Fun building blocks', 'Kids love it. Great for creativity.', true, 12, 0, 'APPROVED'),
(14, 14, 14, 3, 'Average quality', 'Works fine but could be better.', true, 2, 3, 'APPROVED'),
(15, 15, 15, 4, 'Good engine oil', 'Keeps engine running smoothly.', true, 8, 1, 'APPROVED'); 