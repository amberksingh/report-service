INSERT INTO reports
(order_id, user_id, category, product_name, amount, refunded, payment_method, channel, region,
 order_date, delivery_days, rating, is_high_priority)
VALUES
(101, 11, 'ELECTRONICS', 'Laptop', 75000.50, false, 'CARD', 'WEB', 'NORTH', '2025-11-10 22:34:16', 3, 5, false),
(102, 12, 'FASHION', 'Shoes', 4500.00, false, 'UPI', 'MOBILE', 'SOUTH', '2025-10-21 22:34:16', 5, 4, false),
(103, 13, 'GROCERY', 'Rice Bag', 1200.99, true, 'COD', 'WEB', 'WEST', '2025-11-15 22:34:16', 2, 3, true),
(104, 14, 'BOOKS', 'Spring Boot Guide', 799.00, false, 'NETBANKING', 'MOBILE', 'EAST', '2025-10-31 22:34:16', 4, 5, false),
(105, 15, 'ELECTRONICS', 'Keyboard', 1999.00, false, 'CARD', 'WEB', 'NORTH', '2025-10-06 22:34:16', 3, 4, false),
(106, 16, 'FASHION', 'Jacket', 3499.00, false, 'UPI', 'MOBILE', 'SOUTH', '2025-11-08 22:34:16', 6, 5, false),
(107, 17, 'GROCERY', 'Oil 5L', 899.00, false, 'COD', 'WEB', 'WEST', '2025-11-02 22:34:16', 1, 3, false),
(108, 18, 'BOOKS', 'Java Streams Deep Dive', 1299.00, false, 'CARD', 'WEB', 'EAST', '2025-11-16 22:34:16', 2, 5, true),
(109, 19, 'ELECTRONICS', 'Mouse', 799.00, false, 'NETBANKING', 'MOBILE', 'NORTH', '2025-11-12 22:34:16', 3, 4, false),
(110, 20, 'FASHION', 'Backpack', 1599.00, true, 'UPI', 'WEB', 'SOUTH', '2025-10-26 22:34:16', 5, 2, false),

(111, 21, 'GROCERY', 'Milk Pack', 59.00, false, 'COD', 'MOBILE', 'WEST', '2025-11-06 22:34:16', 1, 4, false),
(112, 22, 'BOOKS', 'Kubernetes Mastery', 1599.00, false, 'CARD', 'WEB', 'EAST', '2025-10-29 22:34:16', 3, 5, true),
(113, 23, 'ELECTRONICS', 'Charger', 999.00, false, 'UPI', 'MOBILE', 'NORTH', '2025-11-14 22:34:16', 2, 4, false),
(114, 24, 'FASHION', 'Watch', 4999.00, true, 'CARD', 'WEB', 'SOUTH', '2025-11-11 22:34:16', 7, 2, false),
(115, 25, 'GROCERY', 'Fruits Combo', 450.00, false, 'COD', 'MOBILE', 'WEST', '2025-10-11 22:34:16', 1, 3, false),
(116, 26, 'BOOKS', 'Microservices Patterns', 1999.00, false, 'NETBANKING', 'WEB', 'EAST', '2025-11-17 22:34:16', 4, 5, true),
(117, 27, 'ELECTRONICS', 'Earphones', 1299.00, false, 'UPI', 'MOBILE', 'NORTH', '2025-11-04 22:34:16', 2, 4, false),
(118, 28, 'FASHION', 'Sunglasses', 2499.00, false, 'CARD', 'WEB', 'SOUTH', '2025-10-23 22:34:16', 3, 5, false),
(119, 29, 'GROCERY', 'Chocolate Pack', 299.00, true, 'COD', 'MOBILE', 'WEST', '2025-11-09 22:34:16', 1, 3, true),
(120, 30, 'BOOKS', 'AWS Lambda Essentials', 999.00, false, 'UPI', 'WEB', 'EAST', '2025-11-13 22:34:16', 5, 5, false);
