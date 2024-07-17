package com.anderson.hotel_reservation_system.core.reservation.builder;

import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.DateRangeDTO;
import com.anderson.hotel_reservation_system.core.reservation.dtos.ReservationDTO;
import com.anderson.hotel_reservation_system.core.reservation.enums.ReservationStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilderTest.toCustomer;
import static com.anderson.hotel_reservation_system.core.room.builder.RoomBuilderTest.toRoom;

public class ReservationBuilderTest {
    public static ReservationDTO toReservationDTO() {
        return new ReservationDTO(UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDate.of(2024, 7, 17),
                LocalDate.of(2024, 7, 20));
    }

    public static Reservation toReservation() {
        return new ReservationBuilder()
                .withId(UUID.randomUUID())
                .withCheckIn(LocalDate.of(2024, 7, 17))
                .withCheckOut(LocalDate.of(2024, 7, 20))
                .withStatus(ReservationStatus.SCHEDULED)
                .withCustomer(toCustomer())
                .withRoom(toRoom())
                .build();
    }

    public static DateRangeDTO toDateRangeDTO() {
        return new DateRangeDTO(LocalDate.of(2024, 7, 17), LocalDate.of(2024, 7, 20));
    }

    public static List<Reservation> toReservationListSize2() {
        return List.of( new ReservationBuilder()
                .withId(UUID.randomUUID())
                .withCheckIn(LocalDate.of(2024, 7, 17))
                .withCheckOut(LocalDate.of(2024, 7, 20))
                .withStatus(ReservationStatus.SCHEDULED)
                .withCustomer(toCustomer())
                .withRoom(toRoom())
                .build(),
                new ReservationBuilder()
                        .withId(UUID.randomUUID())
                        .withCheckIn(LocalDate.of(2024, 7, 17))
                        .withCheckOut(LocalDate.of(2024, 7, 20))
                        .withStatus(ReservationStatus.SCHEDULED)
                        .withCustomer(toCustomer())
                        .withRoom(toRoom())
                        .build());
    }
}
