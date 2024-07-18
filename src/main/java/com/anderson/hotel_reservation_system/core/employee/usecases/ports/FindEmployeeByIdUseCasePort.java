package com.anderson.hotel_reservation_system.core.employee.usecases.ports;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;

import java.util.UUID;

public interface FindEmployeeByIdUseCasePort {
    Employee execute(UUID id);
}
