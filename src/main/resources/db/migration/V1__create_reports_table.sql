CREATE TABLE IF NOT EXISTS reports (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_id BIGINT,
    user_id BIGINT,

    category VARCHAR(255),
    product_name VARCHAR(255),

    amount DOUBLE NOT NULL,
    refunded BOOLEAN NOT NULL,

    payment_method VARCHAR(255),
    channel VARCHAR(50),
    region VARCHAR(50),

    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delivery_days INT,
    rating INT,

    is_high_priority BOOLEAN NOT NULL,

    PRIMARY KEY (id)
);
