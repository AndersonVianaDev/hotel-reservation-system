package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.CUSTOMER_NOT_FOUND;

public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCasePort {

    private final CustomerRepository repository;

    public FindCustomerByIdUseCaseImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
    }
}
