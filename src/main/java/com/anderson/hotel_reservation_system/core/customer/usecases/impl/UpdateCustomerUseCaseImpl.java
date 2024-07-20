package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.UpdateCustomerUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static java.util.Objects.nonNull;

public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(UpdateCustomerUseCaseImpl.class);

    private final CustomerRepository repository;

    private final FindCustomerByIdUseCasePort findCustomerById;

    public UpdateCustomerUseCaseImpl(CustomerRepository repository, FindCustomerByIdUseCasePort findCustomerById) {
        this.repository = repository;
        this.findCustomerById = findCustomerById;
    }

    @Override
    public Customer execute(UUID id, CustomerDTO dto) {
        log.debug("Attempting to update customer with id: {}", id);
        Customer customer = findCustomerById.execute(id);

        if(nonNull(dto.name()) && !dto.name().equals(customer.getName())) {
            log.debug("Updating customer name from '{}' to '{}'", customer.getName(), dto.name());
            customer.setName(dto.name());
        }
        if(nonNull(dto.email()) && !dto.email().equals(customer.getEmail())) {
            log.debug("Updating customer email from '{}' to '{}'", customer.getEmail(), dto.email());
            customer.setEmail(dto.email());
        }
        if(nonNull(dto.phone()) && !dto.phone().equals(customer.getPhone())) {
            log.debug("Updating customer phone from '{}' to '{}'", customer.getPhone(), dto.phone());
            customer.setPhone(dto.phone());
        }

        return repository.update(customer);
    }
}
