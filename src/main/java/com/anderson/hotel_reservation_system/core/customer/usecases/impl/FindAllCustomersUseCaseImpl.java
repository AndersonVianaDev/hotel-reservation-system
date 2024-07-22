package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindAllCustomersUseCasePort;

import java.util.List;

public class FindAllCustomersUseCaseImpl implements FindAllCustomersUseCasePort {

    private final CustomerRepository repository;

    public FindAllCustomersUseCaseImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> execute() {
        return repository.findAll();
    }
}
