package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilderTest.toCustomerList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllCustomersUseCaseImplTest {

    @InjectMocks
    private FindAllCustomersUseCaseImpl useCase;

    @Mock
    private CustomerRepository repository;

    @Test
    @DisplayName("find all employees successfully")
    void execute() {
        List<Customer> customers = toCustomerList();

        when(repository.findAll()).thenReturn(customers);

        List<Customer> customersResul = useCase.execute();

        assertEquals(customers, customersResul);
    }
}