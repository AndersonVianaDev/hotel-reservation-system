package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.CUSTOMER_NOT_FOUND;

public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(FindCustomerByIdUseCaseImpl.class);

    private final CustomerRepository repository;

    public FindCustomerByIdUseCaseImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer execute(UUID id) {
        log.debug("Attempting to find customer with id: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            log.warn("Employee with id {} not found", id);
            return new NotFoundException(CUSTOMER_NOT_FOUND);
        });
    }
}
