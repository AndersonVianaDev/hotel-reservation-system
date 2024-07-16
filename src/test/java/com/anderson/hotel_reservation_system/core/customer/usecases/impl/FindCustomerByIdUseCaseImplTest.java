package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilderTest.toCustomer;
import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.CUSTOMER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCustomerByIdUseCaseImplTest {

    @InjectMocks
    private FindCustomerByIdUseCaseImpl useCase;

    @Mock
    private CustomerRepository repository;

    @Test
    @DisplayName("successfully find customer by ID")
    void execute() {
        Customer customer = toCustomer();
        UUID id = customer.getId();

        when(repository.findById(id)).thenReturn(Optional.of(customer));

        Customer customerResult = useCase.execute(id);

        assertEquals(customer, customerResult);
    }

    @Test
    @DisplayName("customer not found")
    void executeWithCustomerNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> useCase.execute(id));

        assertEquals(CUSTOMER_NOT_FOUND, exception.getMessage());
    }
}