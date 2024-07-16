package com.anderson.hotel_reservation_system.core.customer.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Customer {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private Instant create_at;

    public Customer(UUID id, Instant create_at, String phone, String email, String name) {
        this.id = id;
        this.create_at = create_at;
        this.phone = phone;
        this.email = email;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
