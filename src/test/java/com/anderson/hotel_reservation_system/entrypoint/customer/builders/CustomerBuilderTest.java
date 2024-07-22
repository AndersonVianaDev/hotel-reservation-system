package com.anderson.hotel_reservation_system.entrypoint.customer.builders;

import com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilder;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;

import java.util.List;

public class CustomerBuilderTest {

    public static CustomerDTO toCustomerDTO() {
        return new CustomerDTO("dto1", "test1@gmail.com", "999999999");
    }

    public static Customer toCustomer1() {
        return new CustomerBuilder()
                .withName("test1")
                .withEmail("test1@gmail.com")
                .withPhone("999999999")
                .build();
    }

    public static Customer toCustomer2() {
        return new CustomerBuilder()
                .withName("test2")
                .withEmail("test2@gmail.com")
                .withPhone("888888888")
                .build();
    }

    public static Customer toCustomer3() {
        return new CustomerBuilder()
                .withName("test3")
                .withEmail("test3@gmail.com")
                .withPhone("777777777")
                .build();
    }

    public static List<Customer> toCustomers() {
        return List.of(toCustomer1(), toCustomer2(), toCustomer3());
    }

}
