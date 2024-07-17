package com.anderson.hotel_reservation_system.dataprovider.room.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_rooms")
public class RoomEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String roomNumber;
    private String type;
    private BigDecimal price;

    public RoomEntity(UUID id, String roomNumber, String type, BigDecimal price) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
