CREATE TABLE customers (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    date_of_birth DATE
);
CREATE TABLE pets (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    breed VARCHAR(100),
    color VARCHAR(50),
    year_of_birth INT,
    start_at DATE,
    end_at DATE,
    customer_id UUID NOT NULL,
        CONSTRAINT fk_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
        ON DELETE CASCADE
);
