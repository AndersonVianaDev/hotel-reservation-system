package com.anderson.hotel_reservation_system.core.employee.usecases.impl;

import com.anderson.hotel_reservation_system.core.employee.dataprovider.EmployeeRepository;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.employee.builder.EmployeeBuilderTest.toEmployee;
import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.EMPLOYEE_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindEmployeeByIdUseCaseImplTest {

    @InjectMocks
    private FindEmployeeByIdUseCaseImpl useCase;

    @Mock
    private EmployeeRepository repository;

    @Test
    @DisplayName("successfully find employee by ID")
    void execute() {
        Employee employee = toEmployee();
        UUID id = employee.getId();

        when(repository.findById(id)).thenReturn(Optional.of(employee));

        Employee employeeResul = useCase.execute(id);

        assertEquals(employee, employeeResul);
    }

    @Test
    @DisplayName("employee not found")
    void executeWithEmployeeNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> useCase.execute(id));
        
        assertEquals(EMPLOYEE_NOT_FOUND, exception.getMessage());
    }
}