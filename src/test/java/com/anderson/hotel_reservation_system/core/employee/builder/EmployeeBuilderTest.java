package com.anderson.hotel_reservation_system.core.employee.builder;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.core.employee.enums.EmployeeType;

import java.util.UUID;

public class EmployeeBuilderTest {
    public static EmployeeDTO toEmployeeDTO() {
        return new EmployeeDTO("Anderson", "anderson@gmail.com", "anderson12", "admin");
    }

    public static Employee toEmployee() {
        return new EmployeeBuilder()
                .withId(UUID.randomUUID())
                .withName("Anderson")
                .withEmail("anderson@gmail.com")
                .withPassword("anderson12")
                .withType(EmployeeType.ADMIN)
                .build();
    }
}
