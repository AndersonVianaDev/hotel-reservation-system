package com.anderson.hotel_reservation_system.entrypoint.customer.mapper;

import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.entrypoint.customer.dtos.CustomerRequestDTO;

public class CustomerRequestDTOMapper {
    public static CustomerDTO toCustomerDTO(CustomerRequestDTO dto) {
        return new CustomerDTO(dto.name(), dto.email(), dto.phone());
    }
}
