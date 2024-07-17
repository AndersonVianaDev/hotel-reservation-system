package com.anderson.hotel_reservation_system.core.reservation.builder;

import com.anderson.hotel_reservation_system.core.reservation.dtos.ReservationDTO;

import java.time.LocalDate;
import java.util.UUID;

public class ReservationBuilderTest {
    public static ReservationDTO toReservationDTO() {
        return new ReservationDTO(UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDate.of(2024, 7, 17),
                LocalDate.of(2024, 7, 20));
    }
}
