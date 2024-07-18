package com.anderson.hotel_reservation_system.core.employee.usecases.impl;

import com.anderson.hotel_reservation_system.core.employee.dataprovider.EmployeeRepository;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.FindEmployeeByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.EMPLOYEE_NOT_FOUND;

public class FindEmployeeByIdUseCaseImpl implements FindEmployeeByIdUseCasePort {

    private EmployeeRepository repository;

    public FindEmployeeByIdUseCaseImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND));
    }
}
