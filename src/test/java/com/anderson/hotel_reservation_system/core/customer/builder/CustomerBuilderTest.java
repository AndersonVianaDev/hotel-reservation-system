package com.anderson.hotel_reservation_system.core.customer.builder;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class CustomerBuilderTest {
    public static CustomerDTO toCustomerDTO() {
        return new CustomerDTO("anderson", "anderson@gmail.com", "9999999999");
    }

    public static Customer toCustomer() {
        return new CustomerBuilder()
                .withId(UUID.randomUUID())
                .withName("anderson")
                .withEmail("anderson@gmail.com")
                .withPhone("9999999999")
                .withCreate_at(Instant.now())
                .build();
    }

    public static List<Customer> toCustomerList() {
        return List.of(toCustomer(), toCustomer());
    }
}
