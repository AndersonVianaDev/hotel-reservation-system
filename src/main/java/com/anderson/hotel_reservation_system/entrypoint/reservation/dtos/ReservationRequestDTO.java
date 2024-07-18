package com.anderson.hotel_reservation_system.entrypoint.reservation.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationRequestDTO(
        @NotNull(message = "check-in date must not be null")
        @FutureOrPresent(message = "check-in date must be in the present or future") LocalDate checkIn,
        @NotNull(message = "check-out date must not be null")
        @FutureOrPresent(message = "check-out date must be in the present or future") LocalDate checkOut) {
}
