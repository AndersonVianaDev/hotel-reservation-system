package com.anderson.hotel_reservation_system.core.employee.dataprovider;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository {
    Employee save(Employee employee);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findById(UUID id);
    void delete(Employee employee);
}
