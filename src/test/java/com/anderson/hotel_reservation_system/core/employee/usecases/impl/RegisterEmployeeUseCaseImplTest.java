package com.anderson.hotel_reservation_system.core.employee.usecases.impl;

import com.anderson.hotel_reservation_system.core.employee.dataprovider.EmployeeRepository;
import com.anderson.hotel_reservation_system.core.employee.dataprovider.PasswordEncoderPort;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.core.exceptions.DataConflictException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.employee.builder.EmployeeBuilderTest.toEmployee;
import static com.anderson.hotel_reservation_system.core.employee.builder.EmployeeBuilderTest.toEmployeeDTO;
import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.EMAIL_ALREADY_REGISTERED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterEmployeeUseCaseImplTest {

    @InjectMocks
    private RegisterEmployeeUseCaseImpl useCase;

    @Mock
    private PasswordEncoderPort passwordEncoder;

    @Mock
    private EmployeeRepository repository;

    @Test
    @DisplayName("successfully registering employee")
    void execute() {
        EmployeeDTO dto = toEmployeeDTO();

        when(passwordEncoder.encode(dto.password())).thenReturn("21345676");
        when(repository.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(repository.save(any(Employee.class))).thenAnswer(invocations -> invocations.getArgument(0));

        Employee employee = useCase.execute(dto);

        assertEquals(dto.name(), employee.getName());
        assertEquals(dto.email(), employee.getEmail());
        assertNotEquals(dto.password(), employee.getPassword());
    }

    @Test
    @DisplayName("data conflict exception")
    void executeWithDataConflict() {
        EmployeeDTO dto = toEmployeeDTO();
        Employee employee = toEmployee();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(employee));

        DataConflictException exception = assertThrows(DataConflictException.class, () -> useCase.execute(dto));

        assertEquals(EMAIL_ALREADY_REGISTERED, exception.getMessage());
    }
}