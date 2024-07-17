package com.anderson.hotel_reservation_system.dataprovider.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_customers")
public class CustomerEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String phone;
    @CreationTimestamp
    private Instant create_at;

    public CustomerEntity(UUID id, String name, String email, String phone, Instant create_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.create_at = create_at;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Instant getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Instant create_at) {
        this.create_at = create_at;
    }
}
