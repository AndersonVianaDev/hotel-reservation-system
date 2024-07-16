package com.anderson.hotel_reservation_system.core.customer.usecases.ports;

import java.util.UUID;

public interface DeleteCustomerUseCasePort {
    void delete(UUID id);
}
