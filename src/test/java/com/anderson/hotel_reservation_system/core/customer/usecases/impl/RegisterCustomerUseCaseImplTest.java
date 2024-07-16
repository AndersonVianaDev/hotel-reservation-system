package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilder;
import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.core.exceptions.DataConflictException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilderTest.toCustomer;
import static com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilderTest.toCustomerDTO;
import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.EMAIL_ALREADY_REGISTERED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterCustomerUseCaseImplTest {

    @InjectMocks
    private RegisterCustomerUseCaseImpl useCase;

    @Mock
    private CustomerRepository repository;

    @Test
    @DisplayName("successfully registering customer")
    void execute() {
        CustomerDTO dto = toCustomerDTO();

        when(repository.save(any(Customer.class))).thenAnswer(invocations -> invocations.getArgument(0));
        when(repository.findByEmail(dto.email())).thenReturn(Optional.empty());

        Customer customer = useCase.execute(dto);

        assertEquals(dto.name(), customer.getName());
        assertEquals(dto.email(), customer.getEmail());
        assertEquals(dto.phone(), customer.getPhone());
    }

    @Test
    @DisplayName("data conflict exception")
    void executeWithDataConflict() {
        CustomerDTO dto = toCustomerDTO();
        Customer customer = toCustomer();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(customer));

        DataConflictException exception = assertThrows(DataConflictException.class, () -> useCase.execute(dto));

        assertEquals(EMAIL_ALREADY_REGISTERED, exception.getMessage());
    }
}