package com.anderson.hotel_reservation_system.entrypoint.reservation.builders;

import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.ReservationRequestDTO;

import java.time.LocalDate;

public class ReservationBuilderTest {
    public static ReservationRequestDTO toReservationRequestDTO() {
        return new ReservationRequestDTO(LocalDate.of(2024, 7, 24), LocalDate.of(2024, 7, 28));
    }

    public static ReservationRequestDTO toReservationRequestDTOWithInvalidData() {
        return new ReservationRequestDTO(LocalDate.of(2024, 7, 28), LocalDate.of(2024, 7, 24));
    }
}
