CREATE TABLE tb_employees(
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    type VARCHAR(10) NOT NULL
    CONSTRAINT valid_type CHECK (type in ('ADMIN', 'COMMON'))
);