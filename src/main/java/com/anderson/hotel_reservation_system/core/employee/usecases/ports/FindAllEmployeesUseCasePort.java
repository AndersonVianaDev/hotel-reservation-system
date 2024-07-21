package com.anderson.hotel_reservation_system.core.employee.usecases.ports;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;

import java.util.List;

public interface FindAllEmployeesUseCasePort {
    List<Employee> execute();
}
