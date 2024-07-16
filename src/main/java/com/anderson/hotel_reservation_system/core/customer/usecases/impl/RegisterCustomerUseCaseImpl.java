package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.RegisterCustomerUseCasePort;

import static com.anderson.hotel_reservation_system.core.customer.mapper.CustomerMapper.toCustomer;

public class RegisterCustomerUseCaseImpl implements RegisterCustomerUseCasePort {

    private final CustomerRepository repository;

    public RegisterCustomerUseCaseImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer execute(CustomerDTO dto) {
        if(repository.findByEmail(dto.email()).isPresent()) throw new RuntimeException("email already registered");
        Customer customer = toCustomer(dto);
        return repository.save(customer);
    }
}
