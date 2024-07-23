package com.anderson.hotel_reservation_system.entrypoint.reservation.builders;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.reservation.builder.ReservationBuilder;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.enums.ReservationStatus;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.ReservationRequestDTO;

import java.time.LocalDate;

public class ReservationBuilderTest {
    public static ReservationRequestDTO toReservationRequestDTO() {
        return new ReservationRequestDTO(LocalDate.of(2024, 7, 24), LocalDate.of(2024, 7, 28));
    }

    public static ReservationRequestDTO toReservationRequestDTOWithInvalidData() {
        return new ReservationRequestDTO(LocalDate.of(2024, 7, 28), LocalDate.of(2024, 7, 24));
    }

    public static Reservation toReservation(Customer customer, Room room) {
        return new ReservationBuilder()
                .withCustomer(customer)
                .withRoom(room)
                .withCheckIn(LocalDate.of(2024, 7, 24))
                .withCheckOut(LocalDate.of(2024, 7, 28))
                .withStatus(ReservationStatus.SCHEDULED)
                .build();
    }
}
