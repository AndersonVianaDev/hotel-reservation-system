package com.anderson.hotel_reservation_system.entrypoint.reservation.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DateRangeRequestDTO(@NotNull(message = "Start date must not be null") LocalDate startDate,
                                  @NotNull(message = "End date must not be null") LocalDate endDate) {
}
