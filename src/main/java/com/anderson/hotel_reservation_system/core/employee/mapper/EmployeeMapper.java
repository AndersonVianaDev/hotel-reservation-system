package com.anderson.hotel_reservation_system.core.employee.mapper;

import com.anderson.hotel_reservation_system.core.employee.builder.EmployeeBuilder;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.core.employee.enums.EmployeeType;

public class EmployeeMapper {
    public static Employee toEmployee(EmployeeDTO dto, String encodedPassword) {
        return new EmployeeBuilder()
                .withName(dto.name())
                .withEmail(dto.email())
                .withPassword(encodedPassword)
                .withType(EmployeeType.fromString(dto.type()))
                .build();
    }
}
