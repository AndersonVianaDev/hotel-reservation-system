package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.UpdateCustomerUseCasePort;

import java.util.UUID;

import static java.util.Objects.nonNull;

public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCasePort {

    private final CustomerRepository repository;

    private final FindCustomerByIdUseCasePort findCustomerById;

    public UpdateCustomerUseCaseImpl(CustomerRepository repository, FindCustomerByIdUseCasePort findCustomerById) {
        this.repository = repository;
        this.findCustomerById = findCustomerById;
    }

    @Override
    public Customer execute(UUID id, CustomerDTO dto) {
        Customer customer = findCustomerById.execute(id);

        if(nonNull(dto.name()) && !dto.name().equals(customer.getName())) {
            customer.setName(dto.name());
        }
        if(nonNull(dto.email()) && !dto.email().equals(customer.getEmail())) {
            customer.setEmail(dto.email());
        }
        if(nonNull(dto.phone()) && !dto.phone().equals(customer.getPhone())) {
            customer.setPhone(dto.phone());
        }

        return repository.save(customer);
    }
}
