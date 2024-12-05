
    CREATE TABLE IF NOT EXISTS products (
        product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255),
        description VARCHAR(255),
        price DOUBLE,
        quantity_in_stock INT);

    CREATE TABLE IF NOT EXISTS customers (
        customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        first_name VARCHAR(255),
        last_name VARCHAR(255),
        email VARCHAR(255),
        contact_number VARCHAR(255));


    CREATE TABLE IF NOT EXISTS orders (
        order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        customer_id BIGINT,
        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        shipping_address VARCHAR(255),
        total_price DOUBLE,
        order_status VARCHAR(255),
        FOREIGN KEY (customer_id) REFERENCES customers(customer_id));

    CREATE TABLE IF NOT EXISTS order_product (
        order_id BIGINT,
        product_id BIGINT,
        PRIMARY KEY (order_id, product_id),
        FOREIGN KEY (order_id) REFERENCES orders(order_id),
        FOREIGN KEY (product_id) REFERENCES products(product_id));
