package com.anderson.hotel_reservation_system.dataprovider.customer.mapper;

import com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilder;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.dataprovider.customer.builder.CustomerEntityBuilder;
import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;

import java.util.Optional;

public class CustomerEntityMapper {
    public static CustomerEntity toCustomerEntity(Customer customer) {
        return new CustomerEntityBuilder()
                .withName(customer.getName())
                .withEmail(customer.getEmail())
                .withPhone(customer.getPhone())
                .build();
    }

    public static Customer toCustomer(CustomerEntity customerEntity) {
        return new CustomerBuilder()
                .withId(customerEntity.getId())
                .withName(customerEntity.getName())
                .withEmail(customerEntity.getEmail())
                .withPhone(customerEntity.getPhone())
                .withCreate_at(customerEntity.getCreate_at())
                .build();
    }

    public static Optional<Customer> toOptionalCustomer(Optional<CustomerEntity> customerEntity) {
        return customerEntity.map(CustomerEntityMapper::toCustomer);
    }
}
