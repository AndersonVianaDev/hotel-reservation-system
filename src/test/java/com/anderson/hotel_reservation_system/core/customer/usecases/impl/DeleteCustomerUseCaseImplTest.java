package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilderTest.toCustomer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteCustomerUseCaseImplTest {

    @InjectMocks
    private DeleteCustomerUseCaseImpl useCase;

    @Mock
    private FindCustomerByIdUseCasePort findCustomerById;

    @Mock
    private CustomerRepository repository;

    @Test
    @DisplayName("successfully deleting customer")
    void execute() {
        Customer customer = toCustomer();
        UUID id = customer.getId();

        when(findCustomerById.execute(id)).thenReturn(customer);

        useCase.execute(id);

        verify(repository, times(1)).delete(customer);
    }

}