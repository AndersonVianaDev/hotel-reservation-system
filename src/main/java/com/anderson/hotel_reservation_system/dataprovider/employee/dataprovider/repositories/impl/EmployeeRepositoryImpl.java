package com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.impl;

import com.anderson.hotel_reservation_system.core.employee.dataprovider.EmployeeRepository;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.EMPLOYEE_NOT_FOUND;
import static com.anderson.hotel_reservation_system.dataprovider.employee.mapper.EmployeeEntityMapper.*;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private SpringEmployeeRepository repository;

    @Override
    @Transactional
    public Employee save(Employee employee) {
        EmployeeEntity employeeEntity = toEmployeeEntity(employee);
        return toEmployee(repository.save(employeeEntity));
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        Optional<EmployeeEntity> employeeEntity = repository.findByEmail(email);
        return toOptionalEmployee(employeeEntity);
    }

    @Override
    public Optional<Employee> findById(UUID id) {
        Optional<EmployeeEntity> employeeEntity = repository.findById(id);
        return toOptionalEmployee(employeeEntity);
    }

    @Override
    @Transactional
    public void delete(Employee employee) {
        EmployeeEntity employeeEntity = repository.findById(employee.getId()).orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND));
        repository.delete(employeeEntity);
    }

    @Override
    public List<Employee> findAll() {
        return toEmployeeList(repository.findAll());
    }

}
