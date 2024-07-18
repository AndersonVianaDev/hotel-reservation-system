package com.anderson.hotel_reservation_system.core.employee.dataprovider;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;

import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);
    Optional<Employee> findByEmail(String email);
}
