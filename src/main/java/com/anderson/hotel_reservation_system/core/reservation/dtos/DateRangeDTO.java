package com.anderson.hotel_reservation_system.core.reservation.dtos;

import java.time.LocalDate;

public record DateRangeDTO(LocalDate checkIn, LocalDate checkOut) {
}
