package com.anderson.hotel_reservation_system.core.customer.builder;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;

import java.time.Instant;
import java.util.UUID;

public class CustomerBuilder {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private Instant create_at;

    public CustomerBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder withCreate_at(Instant create_at) {
        this.create_at = create_at;
        return this;
    }

    public Customer build() {
        return new Customer(id, create_at, phone, email, name);
    }

}
