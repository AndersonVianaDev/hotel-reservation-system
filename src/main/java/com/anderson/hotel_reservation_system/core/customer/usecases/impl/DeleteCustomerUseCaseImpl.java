package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.DeleteCustomerUseCasePort;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;

import java.util.UUID;

public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCasePort {

    private final CustomerRepository repository;

    private final FindCustomerByIdUseCasePort findCustomerById;

    public DeleteCustomerUseCaseImpl(CustomerRepository repository, FindCustomerByIdUseCasePort findCustomerById) {
        this.repository = repository;
        this.findCustomerById = findCustomerById;
    }

    @Override
    public void delete(UUID id) {
        Customer customer = findCustomerById.execute(id);
        repository.delete(customer);
    }
}
