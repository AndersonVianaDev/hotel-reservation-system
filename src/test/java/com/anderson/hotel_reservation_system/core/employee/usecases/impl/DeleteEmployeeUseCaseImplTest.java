package com.anderson.hotel_reservation_system.core.employee.usecases.impl;

import com.anderson.hotel_reservation_system.core.employee.dataprovider.EmployeeRepository;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.FindEmployeeByIdUseCasePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.employee.builder.EmployeeBuilderTest.toEmployee;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteEmployeeUseCaseImplTest {

    @InjectMocks
    private DeleteEmployeeUseCaseImpl useCase;

    @Mock
    private EmployeeRepository repository;

    @Mock
    private FindEmployeeByIdUseCasePort findEmployeeById;

    @Test
    @DisplayName("successfully deleting employee")
    void execute() {
        Employee employee = toEmployee();
        UUID id = employee.getId();

        when(findEmployeeById.execute(id)).thenReturn(employee);

        useCase.execute(id);

        verify(repository, times(1)).delete(employee);
    }
}