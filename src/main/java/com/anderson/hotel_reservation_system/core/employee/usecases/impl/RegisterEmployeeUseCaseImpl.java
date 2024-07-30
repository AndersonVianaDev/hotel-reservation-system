package com.anderson.hotel_reservation_system.core.employee.usecases.impl;

import com.anderson.hotel_reservation_system.core.employee.dataprovider.EmployeeRepository;
import com.anderson.hotel_reservation_system.core.employee.dataprovider.PasswordEncoderPort;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.RegisterEmployeeUseCasePort;
import com.anderson.hotel_reservation_system.core.exceptions.DataConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.anderson.hotel_reservation_system.core.employee.mapper.EmployeeMapper.toEmployee;
import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.EMAIL_ALREADY_REGISTERED;

public class RegisterEmployeeUseCaseImpl implements RegisterEmployeeUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(RegisterEmployeeUseCaseImpl.class);

    private EmployeeRepository repository;

    private PasswordEncoderPort passwordEncoder;

    public RegisterEmployeeUseCaseImpl(EmployeeRepository repository, PasswordEncoderPort passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee execute(EmployeeDTO dto) {
        log.debug("Starting registration process for employee.");

        if(repository.findByEmail(dto.email()).isPresent()) throw new DataConflictException(EMAIL_ALREADY_REGISTERED);

        String encodedPassword = passwordEncoder.encode(dto.password());
        log.debug("Password for employee with email {} encoded successfully", dto.email());

        Employee employee = toEmployee(dto, encodedPassword);

        return repository.save(employee);
    }
}
