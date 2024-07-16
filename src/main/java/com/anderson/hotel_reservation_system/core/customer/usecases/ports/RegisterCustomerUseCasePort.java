package com.anderson.hotel_reservation_system.core.customer.usecases.ports;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;

public interface RegisterCustomerUseCasePort {
    Customer execute(CustomerDTO dto);
}
