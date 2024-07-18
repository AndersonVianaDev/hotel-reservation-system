CREATE TYPE reservation_status AS ENUM (
    'SCHEDULED',
    'IN_USE',
    'ABSENCE',
    'FINISHED',
    'CANCELED'
);

CREATE TABLE tb_reservations(
    id UUID PRIMARY KEY,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    customer_id UUID NOT NULL,
    room_id UUID NOT NULL,
    CONSTRAINT valid_reservation_status CHECK (status IN ('SCHEDULED', 'IN_USE', 'ABSENCE', 'FINISHED', 'CANCELED')),
    FOREIGN KEY (customer_id) REFERENCES tb_customers(id),
    FOREIGN KEY (room_id) REFERENCES tb_rooms(id)
);