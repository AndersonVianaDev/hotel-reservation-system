package com.anderson.hotel_reservation_system.core.customer.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.dataprovider.CustomerRepository;
import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerUseCaseImplTest {

    @InjectMocks
    private UpdateCustomerUseCaseImpl useCase;

    @Mock
    private FindCustomerByIdUseCasePort findCustomerById;

    @Mock
    private CustomerRepository repository;

    @Test
    @DisplayName("successfully updating the customer")
    void execute() {
        Customer customer = toCustomer();
        UUID id = customer.getId();
        CustomerDTO dto = new CustomerDTO("update", "update@gmail.com", "888888888888");

        when(repository.update(any(Customer.class))).thenAnswer(invocations -> invocations.getArgument(0));
        when(findCustomerById.execute(id)).thenReturn(customer);

        Customer customerResult = useCase.execute(id, dto);

        assertEquals(dto.name(), customerResult.getName());
        assertEquals(dto.email(), customerResult.getEmail());
        assertEquals(dto.phone(), customerResult.getPhone());
    }
}