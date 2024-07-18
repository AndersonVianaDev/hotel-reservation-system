CREATE TABLE tb_customers(
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    phone VARCHAR(12) NOT NULL,
    create_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);