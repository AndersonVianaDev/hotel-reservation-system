package com.anderson.hotel_reservation_system.config.beans.employee;

import com.anderson.hotel_reservation_system.core.employee.usecases.impl.DeleteEmployeeUseCaseImpl;
import com.anderson.hotel_reservation_system.core.employee.usecases.impl.FindEmployeeByIdUseCaseImpl;
import com.anderson.hotel_reservation_system.core.employee.usecases.impl.RegisterEmployeeUseCaseImpl;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.DeleteEmployeeUseCasePort;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.FindEmployeeByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.RegisterEmployeeUseCasePort;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.PasswordEncoderImpl;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.impl.EmployeeRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeBeans {

    @Bean
    public RegisterEmployeeUseCasePort registerEmployeeUseCasePort(EmployeeRepositoryImpl repository, PasswordEncoderImpl passwordEncoder) {
        return new RegisterEmployeeUseCaseImpl(repository, passwordEncoder);
    }

    @Bean
    public FindEmployeeByIdUseCasePort findEmployeeByIdUseCasePort(EmployeeRepositoryImpl repository) {
        return new FindEmployeeByIdUseCaseImpl(repository);
    }

    @Bean
    public DeleteEmployeeUseCasePort deleteEmployeeUseCasePort(EmployeeRepositoryImpl repository, FindEmployeeByIdUseCasePort findEmployeeById) {
        return new DeleteEmployeeUseCaseImpl(repository, findEmployeeById);
    }
}
