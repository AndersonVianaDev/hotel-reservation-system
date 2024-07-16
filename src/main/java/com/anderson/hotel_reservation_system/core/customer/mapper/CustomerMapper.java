package com.anderson.hotel_reservation_system.core.customer.mapper;

import com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilder;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;

public class CustomerMapper {

    public static Customer toCustomer(CustomerDTO dto) {
        return new CustomerBuilder()
                .withName(dto.name())
                .withEmail(dto.email())
                .withPhone(dto.phone())
                .build();
    }
}
