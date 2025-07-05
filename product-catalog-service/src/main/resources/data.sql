TRUNCATE product;

INSERT INTO product (id, name, description, price, category, record_status, created_at, updated_at) VALUES
(1, 'Wireless Mouse', 'Ergonomic wireless mouse with USB receiver', 25.99, 'Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Bluetooth Headphones', 'Noise-cancelling over-ear headphones', 89.99, 'Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Water Bottle', 'Insulated stainless steel water bottle - 1L', 15.49, 'Home & Kitchen', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Yoga Mat', 'Non-slip eco-friendly yoga mat', 29.95, 'Sports', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'LED Desk Lamp', 'Adjustable LED lamp with touch control', 34.50, 'Office Supplies', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Smartphone Stand', 'Adjustable aluminum stand for phones and tablets', 12.99, 'Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'USB-C Charger', 'Fast charging USB-C wall charger - 30W', 22.00, 'Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Notebook Set', 'Pack of 3 ruled notebooks for school or office', 9.99, 'Office Supplies', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Desk Organizer', 'Multi-functional desktop organizer tray', 18.75, 'Office Supplies', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Running Shoes', 'Lightweight running shoes for men and women', 59.95, 'Sports', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 'Resistance Bands', 'Set of 5 workout resistance bands with carry bag', 14.95, 'Sports', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(12, 'Coffee Mug', 'Ceramic coffee mug - 350ml, dishwasher safe', 8.49, 'Home & Kitchen', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(13, 'Gaming Keyboard', 'Mechanical RGB keyboard for gaming and typing', 64.99, 'Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(14, 'Laptop Backpack', 'Water-resistant backpack for 15.6" laptops', 39.90, 'Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(15, 'Wall Clock', 'Modern decorative wall clock, silent sweep', 19.99, 'Home & Kitchen', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(16, 'Table Fan', 'Portable USB table fan with 3-speed settings', 23.99, 'Home & Kitchen', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(17, 'Dumbbell Set', 'Pair of neoprene dumbbells - 5kg each', 44.00, 'Sports', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(18, 'Portable Blender', 'Rechargeable blender for smoothies on-the-go', 32.49, 'Home & Kitchen', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(19, 'Stapler Set', 'Mini stapler with staples and remover', 6.99, 'Office Supplies', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(20, 'Pen Holder', 'Mesh pen holder for desk organization', 5.95, 'Office Supplies', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);