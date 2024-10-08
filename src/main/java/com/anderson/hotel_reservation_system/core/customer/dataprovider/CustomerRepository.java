package com.anderson.hotel_reservation_system.core.customer.dataprovider;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findById(UUID id);
    void delete(Customer customer);
    Customer update(Customer customer);
    List<Customer> findAll();
}
