package com.anderson.hotel_reservation_system.core.customer.dataprovider;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findByEmail(String email);
}
