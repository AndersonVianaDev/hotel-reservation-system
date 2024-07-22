package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindAllCustomersUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FindAllCustomersUseCaseImpl implements FindAllCustomersUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(FindAllCustomersUseCaseImpl.class);

    private final CustomerRepository repository;

    public FindAllCustomersUseCaseImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> execute() {
        log.info("Executing find all customers use case");
        return repository.findAll();
    }
}
