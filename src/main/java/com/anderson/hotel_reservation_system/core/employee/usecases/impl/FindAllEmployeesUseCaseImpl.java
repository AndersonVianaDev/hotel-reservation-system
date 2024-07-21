package com.anderson.hotel_reservation_system.core.employee.usecases.impl;

import com.anderson.hotel_reservation_system.core.employee.dataprovider.EmployeeRepository;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.FindAllEmployeesUseCasePort;

import java.util.List;

public class FindAllEmployeesUseCaseImpl implements FindAllEmployeesUseCasePort {

    private final EmployeeRepository repository;

    public FindAllEmployeesUseCaseImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> execute() {
        return repository.findAll();
    }
}
