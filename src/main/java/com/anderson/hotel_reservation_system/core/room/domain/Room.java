package com.anderson.hotel_reservation_system.core.room.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Room {
    private UUID id;
    private String roomNumber;
    private String type;
    private BigDecimal price;

    public Room(UUID id, String roomNumber, String type, BigDecimal price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
