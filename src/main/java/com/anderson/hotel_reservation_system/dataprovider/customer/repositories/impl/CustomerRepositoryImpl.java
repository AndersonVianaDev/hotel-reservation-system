package com.anderson.hotel_reservation_system.dataprovider.customer.repositories.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.port.SpringCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.CUSTOMER_NOT_FOUND;
import static com.anderson.hotel_reservation_system.dataprovider.customer.mapper.CustomerEntityMapper.*;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private SpringCustomerRepository repository;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = toCustomerEntity(customer);
        return toCustomer(repository.save(customerEntity));
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        Optional<CustomerEntity> customerEntity = repository.findByEmail(email);
        return toOptionalCustomer(customerEntity);
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        Optional<CustomerEntity> customerEntity = repository.findById(id);
        return toOptionalCustomer(customerEntity);
    }

    @Override
    public void delete(Customer customer) {
        CustomerEntity customerEntity = repository.findById(customer.getId()).orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
        repository.delete(customerEntity);
    }

    @Override
    public Customer update(Customer customer) {
        CustomerEntity customerEntity = repository.findById(customer.getId()).orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
        customerEntity.setName(customer.getName());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setPhone(customer.getPhone());
        return toCustomer(repository.save(customerEntity));
    }
}
