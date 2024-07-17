package com.anderson.hotel_reservation_system.dataprovider.customer.builder;

import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;

import java.time.Instant;
import java.util.UUID;

public class CustomerEntityBuilder {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private Instant create_at;

    public CustomerEntityBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public CustomerEntityBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerEntityBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerEntityBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerEntityBuilder withCreate_at(Instant create_at) {
        this.create_at = create_at;
        return this;
    }

    public CustomerEntity build() {
        return new CustomerEntity(id, name, email, phone, create_at);
    }
}
