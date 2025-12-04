INSERT INTO reports
(order_id, user_id, category, product_name, amount, refunded, payment_method, channel, region,
 order_date, delivery_days, rating, is_high_priority)
VALUES
(5101, 10, 'Electronics', 'Laptop Pro', 1200.50, false, 'UPI', 'ONLINE', 'North', '2024-01-10 10:15:00', 3, 5, true),
(5102, 11, 'Electronics', 'Laptop Pro', 1180.00, true, 'CARD', 'STORE', 'South', '2024-01-12 14:00:00', 5, 4, false),
(5103, 10, 'Grocery', 'Organic Rice', 40.00, false, 'COD', 'ONLINE', 'South', '2024-02-01 09:22:00', 2, 3, false),
(5104, 13, 'Fashion', 'Winter Jacket', 89.99, false, 'CARD', 'ONLINE', 'East', '2024-02-10 11:45:00', 7, 2, false),
(5105, 11, 'Fashion', 'Sneakers', 110.00, true, 'UPI', 'STORE', 'West', '2024-02-11 10:30:00', 10, 4, true),
(5106, 14, 'Grocery', 'Tea Powder', 12.50, false, 'CARD', 'ONLINE', 'North', '2024-03-01 08:00:00', 1, 5, false),
(5107, 15, 'Electronics', 'Mouse Wireless', 22.00, false, 'UPI', 'ONLINE', 'West', '2024-03-05 15:30:00', 6, 1, false),
(5108, 10, 'Electronics', 'Keyboard Mechanical', 70.00, false, 'CARD', 'STORE', 'North', '2024-03-08 13:00:00', 3, 4, true),
(5109, 16, 'Electronics', 'Bluetooth Speaker', 49.90, false, 'UPI', 'ONLINE', 'South', '2024-03-10 11:00:00', 2, 5, false),
(5110, 11, 'Electronics', 'Bluetooth Speaker', 45.00, true, 'COD', 'STORE', 'East', '2024-03-10 12:00:00', 4, 2, false),

(5111, 20, 'Electronics', 'Smartphone Max', 880.00, false, 'UPI', 'ONLINE', 'North', '2024-04-01 10:00:00', 3, 4, true),
(5112, 20, 'Fashion', 'Shirt Formal', 30.00, false, 'CARD', 'STORE', 'West', '2024-04-02 09:00:00', 9, 3, false),
(5113, 21, 'Fashion', 'Shirt Formal', 29.00, true, 'UPI', 'ONLINE', 'East', '2024-04-03 10:00:00', 10, 1, false),
(5114, 22, 'Grocery', 'Olive Oil', 9.50, false, 'COD', 'ONLINE', 'South', '2024-04-04 11:00:00', 1, 5, true),

(5115, 23, 'Electronics', 'Powerbank', 39.99, false, 'CARD', 'ONLINE', 'West', '2024-05-01 10:30:00', 3, 4, false),
(5116, 23, 'Electronics', 'Powerbank', 42.00, true, 'UPI', 'STORE', 'East', '2024-05-01 11:30:00', 6, 2, false),

(5117, 24, 'Fashion', 'Denim Jeans', 55.00, false, 'UPI', 'ONLINE', 'North', '2024-05-03 12:30:00', 9, 3, true),
(5118, 25, 'Fashion', 'Denim Jeans', 51.00, false, 'CARD', 'STORE', 'West', '2024-05-04 13:30:00', 5, 5, false),

(5119, 15, 'Grocery', 'Milk', 3.50, false, 'COD', 'ONLINE', 'East', '2024-05-06 10:00:00', 1, 4, false),
(5120, 26, 'Grocery', 'Milk', 3.40, true, 'UPI', 'STORE', 'North', '2024-05-06 11:00:00', 2, 1, false),

(5121, 27, 'Electronics', 'Smartwatch', 199.99, false, 'CARD', 'ONLINE', 'South', '2024-06-01 10:00:00', 4, 5, true),
(5122, 27, 'Electronics', 'Smartwatch', 185.00, false, 'UPI', 'STORE', 'North', '2024-06-02 11:00:00', 8, 3, false),

(5123, 28, 'Fashion', 'Cap', 12.00, false, 'CARD', 'ONLINE', 'East', '2024-06-10 10:30:00', 1, 2, false),
(5124, 29, 'Fashion', 'Cap', 11.00, false, 'UPI', 'STORE', 'West', '2024-06-10 12:30:00', 2, 5, true),

(5125, 30, 'Electronics', 'Camera DSLR', 450.00, false, 'UPI', 'ONLINE', 'North', '2024-06-15 11:00:00', 6, 4, true),
(5126, 30, 'Electronics', 'Camera DSLR', 455.00, true, 'CARD', 'STORE', 'East', '2024-06-15 12:00:00', 9, 1, false),

(5127, 31, 'Grocery', 'Sugar', 2.80, false, 'COD', 'ONLINE', 'South', '2024-06-20 09:00:00', 1, 5, false),
(5128, 31, 'Grocery', 'Sugar', 3.10, true, 'CARD', 'STORE', 'North', '2024-06-20 10:00:00', 2, 2, false),

(5129, 32, 'Fashion', 'T-Shirt', 18.00, false, 'UPI', 'ONLINE', 'East', '2024-07-01 11:00:00', 3, 4, true),
(5130, 32, 'Fashion', 'T-Shirt', 17.00, true, 'CARD', 'STORE', 'South', '2024-07-01 12:00:00', 5, 2, false),

(5131, 33, 'Electronics', 'Monitor 24inch', 150.00, false, 'UPI', 'ONLINE', 'West', '2024-07-05 11:00:00', 7, 5, false),
(5132, 33, 'Electronics', 'Monitor 24inch', 148.00, true, 'CARD', 'STORE', 'North', '2024-07-05 12:00:00', 6, 1, false),

(5133, 34, 'Grocery', 'Bread', 1.20, false, 'UPI', 'ONLINE', 'East', '2024-07-10 09:00:00', 1, 4, false),
(5134, 34, 'Grocery', 'Bread', 1.10, true, 'CARD', 'STORE', 'West', '2024-07-10 10:00:00', 2, 2, false),

(5135, 35, 'Electronics', 'Gaming Console', 500.00, false, 'UPI', 'ONLINE', 'North', '2024-07-20 12:00:00', 8, 5, true),
(5136, 36, 'Electronics', 'Gaming Console', 480.00, true, 'CARD', 'STORE', 'South', '2024-07-20 13:00:00', 10, 1, false),

(5137, 37, 'Fashion', 'Jacket', 90.00, false, 'UPI', 'ONLINE', 'North', '2024-07-22 11:00:00', 4, 5, true),
(5138, 37, 'Fashion', 'Jacket', 88.00, true, 'CARD', 'STORE', 'East', '2024-07-22 14:00:00', 6, 2, false),

(5139, 38, 'Grocery', 'Eggs Pack', 2.50, false, 'UPI', 'ONLINE', 'South', '2024-07-25 09:30:00', 1, 5, true),
(5140, 38, 'Grocery', 'Eggs Pack', 2.30, true, 'CARD', 'STORE', 'North', '2024-07-25 10:30:00', 2, 3, false),

(5041, 19, 'Electronics', 'USB Cable', 5.49, false, 'CARD', 'ONLINE', 'North', '2024-01-21 13:31:00', 2, 3, false),
(5042, 20, 'Fashion', 'Leather Wallet', 45.00, false, 'UPI', 'STORE', 'East', '2024-01-23 18:11:00', 4, 4, true),
(5043, 20, 'Fashion', 'Leather Wallet', 45.00, true, 'CARD', 'ONLINE', 'East', '2024-01-24 19:22:00', 7, 2, false),
(5044, 21, 'Grocery', 'Sugar Bag', 5.99, false, 'COD', 'ONLINE', 'South', '2024-01-25 17:41:00', 3, 4, false),
(5045, 22, 'Electronics', 'USB Cable', 5.49, true, 'CARD', 'ONLINE', 'North', '2024-01-26 16:15:00', 4, 1, false);
