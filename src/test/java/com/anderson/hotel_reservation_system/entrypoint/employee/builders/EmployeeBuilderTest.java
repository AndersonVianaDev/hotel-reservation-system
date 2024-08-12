package com.anderson.hotel_reservation_system.entrypoint.employee.builders;

import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.core.employee.enums.EmployeeType;
import com.anderson.hotel_reservation_system.dataprovider.employee.builder.EmployeeEntityBuilder;
import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;
import com.anderson.hotel_reservation_system.entrypoint.employee.dtos.EmployeeRequestDTO;

import java.util.List;

public class EmployeeBuilderTest {

    public static EmployeeDTO toEmployeeDTO() {
        return new EmployeeDTO("test1", "test1@gmail.com", "test1234", "admin");
    }

    public static EmployeeEntity toEmployeeEntity1() {
        return new EmployeeEntityBuilder()
                .withName("test1")
                .withEmail("test1@gmail.com")
                .withPassword("test1234")
                .withType(EmployeeType.ADMIN)
                .build();
    }

    public static EmployeeEntity toEmployeeEntity2() {
        return new EmployeeEntityBuilder()
                .withName("test2")
                .withEmail("test2@gmail.com")
                .withPassword("test1234")
                .withType(EmployeeType.ADMIN)
                .build();
    }

    public static EmployeeEntity toEmployeeEntity3() {
        return new EmployeeEntityBuilder()
                .withName("test3")
                .withEmail("test3@gmail.com")
                .withPassword("test1234")
                .withType(EmployeeType.COMMON)
                .build();
    }

    public static List<EmployeeEntity> toEmployeesEntity() {
        return List.of(toEmployeeEntity1(), toEmployeeEntity2(), toEmployeeEntity3());
    }

    public static EmployeeRequestDTO toEmployeeRequestDTO() {
        return new EmployeeRequestDTO("anderson", "anderson@gmail.com", "1234", "admin");
    }
}
