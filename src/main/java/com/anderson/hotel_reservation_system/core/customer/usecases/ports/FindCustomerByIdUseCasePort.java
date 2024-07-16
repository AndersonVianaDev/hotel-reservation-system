package com.anderson.hotel_reservation_system.core.customer.usecases.ports;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;

import java.util.UUID;

public interface FindCustomerByIdUseCasePort {
    Customer execute(UUID id);
}
