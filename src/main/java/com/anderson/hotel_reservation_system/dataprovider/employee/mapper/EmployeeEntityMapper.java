package com.anderson.hotel_reservation_system.dataprovider.employee.mapper;

import com.anderson.hotel_reservation_system.core.employee.builder.EmployeeBuilder;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.dataprovider.employee.builder.EmployeeEntityBuilder;
import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public class EmployeeEntityMapper {

    public static EmployeeEntity toEmployeeEntity(Employee employee) {
        return new EmployeeEntityBuilder()
                .withName(employee.getName())
                .withEmail(employee.getEmail())
                .withPassword(employee.getPassword())
                .withType(employee.getType())
                .build();
    }

    public static Employee toEmployee(EmployeeEntity employeeEntity) {
        return new EmployeeBuilder()
                .withId(employeeEntity.getId())
                .withName(employeeEntity.getName())
                .withEmail(employeeEntity.getEmail())
                .withPassword(employeeEntity.getPassword())
                .withType(employeeEntity.getType())
                .build();
    }

    public static Optional<Employee> toOptionalEmployee(Optional<EmployeeEntity> employeeEntity) {
        return employeeEntity.map(EmployeeEntityMapper::toEmployee);
    }

    public static List<Employee> toEmployeeList(List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream().map(EmployeeEntityMapper::toEmployee).toList();
    }
}
