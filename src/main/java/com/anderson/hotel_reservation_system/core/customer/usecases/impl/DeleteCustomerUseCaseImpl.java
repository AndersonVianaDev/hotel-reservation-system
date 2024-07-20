package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.DeleteCustomerUseCasePort;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(DeleteCustomerUseCaseImpl.class);

    private final CustomerRepository repository;

    private final FindCustomerByIdUseCasePort findCustomerById;

    public DeleteCustomerUseCaseImpl(CustomerRepository repository, FindCustomerByIdUseCasePort findCustomerById) {
        this.repository = repository;
        this.findCustomerById = findCustomerById;
    }

    @Override
    public void execute(UUID id) {
        log.debug("Attempting to delete customer with id: {}", id);
        Customer customer = findCustomerById.execute(id);
        repository.delete(customer);
    }
}
