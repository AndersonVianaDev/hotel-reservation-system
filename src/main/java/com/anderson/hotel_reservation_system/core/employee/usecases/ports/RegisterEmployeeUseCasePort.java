package com.anderson.hotel_reservation_system.core.employee.usecases.ports;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;

public interface RegisterEmployeeUseCasePort {
    Employee execute(EmployeeDTO dto);
}
