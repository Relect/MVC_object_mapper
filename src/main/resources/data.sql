
    INSERT INTO products (name, description, price, quantity_in_stock) VALUES
    ('product1', 'description1', 50.0, 10),
    ('product1', 'description1', 10.0, 8),
    ('product1', 'description1', 10.0, 5),
    ('product1', 'description1', 10.0, 20),
    ('product1', 'description1', 30.0, 5);

    INSERT INTO customers (first_name, last_name, email, contact_number) VALUES
    ('name1', 'surname1', 'email1@mail.ru', '+7918-580-88-81'),
    ('name2', 'surname2', 'email2@mail.ru', '+7918-580-88-82');

    INSERT INTO orders (customer_id, shipping_address, total_price, order_status) VALUES
    (1, 'rollstret, house1', 60.0, 'PENDING'),
    (1, 'rollstret, house2', 70.0, 'COMPLETE');

    INSERT INTO order_product (order_id, product_id) VALUES
    (1, 1),(1, 2),
    (2, 1),(2, 3),(2, 4);
