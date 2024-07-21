package com.anderson.hotel_reservation_system.core.employee.usecases.impl;

import com.anderson.hotel_reservation_system.core.employee.dataprovider.EmployeeRepository;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.anderson.hotel_reservation_system.core.employee.builder.EmployeeBuilderTest.toEmployeeListSize2;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllEmployeesUseCaseImplTest {

    @InjectMocks
    private FindAllEmployeesUseCaseImpl useCase;

    @Mock
    private EmployeeRepository repository;

    @Test
    @DisplayName("find all employees successfully")
    void execute() {
        List<Employee> employees = toEmployeeListSize2();

        when(repository.findAll()).thenReturn(employees);

        List<Employee> employeesListResult = useCase.execute();

        assertEquals(employees, employeesListResult);
    }
}