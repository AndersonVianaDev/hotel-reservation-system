package com.anderson.hotel_reservation_system.dataprovider.room.builder;

import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class RoomEntityBuilder {
    private UUID id;
    private String roomNumber;
    private String type;
    private BigDecimal price;

    public RoomEntityBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public RoomEntityBuilder withRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public RoomEntityBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public RoomEntityBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public RoomEntity build() {
        return new RoomEntity(id, roomNumber, type, price);
    }
}
