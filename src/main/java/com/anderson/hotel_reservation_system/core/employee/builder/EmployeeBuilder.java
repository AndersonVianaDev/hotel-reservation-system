package com.anderson.hotel_reservation_system.core.employee.builder;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.enums.EmployeeType;

import java.util.UUID;

public class EmployeeBuilder {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private EmployeeType type;

    public EmployeeBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public EmployeeBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public EmployeeBuilder withType(EmployeeType type) {
        this.type = type;
        return this;
    }

    public Employee build() {
        return new Employee(id, name, email, password, type);
    }
}
