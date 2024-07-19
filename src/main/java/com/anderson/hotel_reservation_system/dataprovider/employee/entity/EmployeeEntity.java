package com.anderson.hotel_reservation_system.dataprovider.employee.entity;

import com.anderson.hotel_reservation_system.core.employee.enums.EmployeeType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String password;
    private EmployeeType type;

    private EmployeeEntity() {}

    public EmployeeEntity(UUID id, String name, String email, String password, EmployeeType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }
}
