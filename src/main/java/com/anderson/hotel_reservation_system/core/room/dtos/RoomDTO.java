package com.anderson.hotel_reservation_system.core.room.dtos;

import java.math.BigDecimal;

public record RoomDTO(String roomNumber, String type, BigDecimal price) {
}
