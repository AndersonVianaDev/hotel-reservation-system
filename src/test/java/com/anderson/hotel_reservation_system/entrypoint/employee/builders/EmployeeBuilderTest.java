package com.anderson.hotel_reservation_system.entrypoint.employee.builders;

import com.anderson.hotel_reservation_system.core.employee.builder.EmployeeBuilder;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.core.employee.enums.EmployeeType;
import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;

import java.util.List;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.dataprovider.employee.mapper.EmployeeEntityMapper.toEmployeeEntity;

public class EmployeeBuilderTest {

    public static EmployeeDTO toEmployeeDTO() {
        return new EmployeeDTO("test1", "test1@gmail.com", "test1234", "admin");
    }

    public static Employee toEmployee1() {
        return new EmployeeBuilder()
                .withName("test1")
                .withEmail("test1@gmail.com")
                .withPassword("test1234")
                .withType(EmployeeType.ADMIN)
                .build();
    }

    public static Employee toEmployee2() {
        return new EmployeeBuilder()
                .withName("test2")
                .withEmail("test2@gmail.com")
                .withPassword("test1234")
                .withType(EmployeeType.ADMIN)
                .build();
    }

    public static Employee toEmployee3() {
        return new EmployeeBuilder()
                .withName("test3")
                .withEmail("test3@gmail.com")
                .withPassword("test1234")
                .withType(EmployeeType.COMMON)
                .build();
    }

    public static List<EmployeeEntity> toEmployeesEntity() {
        return List.of(toEmployeeEntity(toEmployee1()), toEmployeeEntity(toEmployee2()), toEmployeeEntity(toEmployee3()));
    }
}
