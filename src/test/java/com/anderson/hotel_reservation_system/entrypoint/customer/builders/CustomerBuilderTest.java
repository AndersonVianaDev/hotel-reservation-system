package com.anderson.hotel_reservation_system.entrypoint.customer.builders;

import com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilder;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.dataprovider.customer.builder.CustomerEntityBuilder;
import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;
import com.anderson.hotel_reservation_system.entrypoint.customer.dtos.CustomerRequestDTO;

import java.util.List;

public class CustomerBuilderTest {

    public static CustomerDTO toCustomerDTO() {
        return new CustomerDTO("dto1", "test1@gmail.com", "999999999");
    }

    public static CustomerEntity toCustomerEntity1() {
        return new CustomerEntityBuilder()
                .withName("test1")
                .withEmail("test1@gmail.com")
                .withPhone("999999999")
                .build();
    }

    public static CustomerEntity toCustomerEntity2() {
        return new CustomerEntityBuilder()
                .withName("test2")
                .withEmail("test2@gmail.com")
                .withPhone("888888888")
                .build();
    }

    public static CustomerEntity toCustomerEntity3() {
        return new CustomerEntityBuilder()
                .withName("test3")
                .withEmail("test3@gmail.com")
                .withPhone("777777777")
                .build();
    }

    public static List<CustomerEntity> toCustomersEntity() {
        return List.of(toCustomerEntity1(), toCustomerEntity2(), toCustomerEntity3());
    }

    public static CustomerRequestDTO toCustomerRequestDTO() {
        return new CustomerRequestDTO("anderson", "anderson", "986454678");
    }

}
