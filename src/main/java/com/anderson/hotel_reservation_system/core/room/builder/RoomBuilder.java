package com.anderson.hotel_reservation_system.core.room.builder;

import com.anderson.hotel_reservation_system.core.room.domain.Room;

import java.math.BigDecimal;
import java.util.UUID;

public class RoomBuilder {
    private UUID id;
    private String roomNumber;
    private String type;
    private BigDecimal price;

    public RoomBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public RoomBuilder withRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public RoomBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public RoomBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Room build() {
        return new Room(id, roomNumber, type, price);
    }
}
