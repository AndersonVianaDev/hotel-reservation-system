CREATE TABLE tb_rooms(
    id UUID PRIMARY KEY,
    room_number VARCHAR(10) NOT NULL UNIQUE,
    type VARCHAR(20) NOT NULL,
    price NUMERIC(10, 2) NOT NULL
);