package com.anderson.hotel_reservation_system.core.reservation.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record ReservationDTO(UUID idCustomer, UUID idRoom, LocalDate checkIn, LocalDate checkOut) {
}
