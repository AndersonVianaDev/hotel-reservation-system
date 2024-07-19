package com.anderson.hotel_reservation_system.dataprovider.employee.builder;

import com.anderson.hotel_reservation_system.core.employee.enums.EmployeeType;
import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;

import java.util.UUID;

public class EmployeeEntityBuilder {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private EmployeeType type;

    public EmployeeEntityBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public EmployeeEntityBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeEntityBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public EmployeeEntityBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public EmployeeEntityBuilder withType(EmployeeType type) {
        this.type = type;
        return this;
    }

    public EmployeeEntity build() {
        return new EmployeeEntity(id, name, email, password, type);
    }
}
