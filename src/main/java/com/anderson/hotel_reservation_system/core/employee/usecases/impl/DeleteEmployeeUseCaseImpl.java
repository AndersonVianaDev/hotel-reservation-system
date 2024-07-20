package com.anderson.hotel_reservation_system.core.employee.usecases.impl;

import com.anderson.hotel_reservation_system.core.employee.dataprovider.EmployeeRepository;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.DeleteEmployeeUseCasePort;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.FindEmployeeByIdUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class DeleteEmployeeUseCaseImpl implements DeleteEmployeeUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(DeleteEmployeeUseCaseImpl.class);

    private EmployeeRepository repository;

    private FindEmployeeByIdUseCasePort findEmployeeById;

    public DeleteEmployeeUseCaseImpl(EmployeeRepository repository, FindEmployeeByIdUseCasePort findEmployeeById) {
        this.repository = repository;
        this.findEmployeeById = findEmployeeById;
    }

    @Override
    public void execute(UUID id) {
        log.debug("Attempting to delete employee with id: {}", id);
        Employee employee = findEmployeeById.execute(id);
        repository.delete(employee);
    }
}
