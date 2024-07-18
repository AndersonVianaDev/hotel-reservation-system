package com.anderson.hotel_reservation_system.entrypoint.room.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record RoomRequestDTO(@NotNull(message = "room number field cannot be null.") @Size(min = 1, max = 6, message = "the room number must be between 1 to 6 digits.") String roomNumber,
                             @NotNull(message = "type field cannot be null.") String type,
                             @NotNull(message = "price field cannot be null.") @Positive(message = "price has to be positive.") BigDecimal price) {
}
