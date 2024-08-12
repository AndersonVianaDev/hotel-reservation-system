package com.anderson.hotel_reservation_system.entrypoint.reservation.builders;

import com.anderson.hotel_reservation_system.core.reservation.enums.ReservationStatus;
import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;
import com.anderson.hotel_reservation_system.dataprovider.reservation.builder.ReservationEntityBuilder;
import com.anderson.hotel_reservation_system.dataprovider.reservation.entity.ReservationEntity;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;
import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.ReservationRequestDTO;

import java.time.LocalDate;

public class ReservationBuilderTest {
    public static ReservationRequestDTO toReservationRequestDTO() {
        return new ReservationRequestDTO(LocalDate.now(), LocalDate.now().plusDays(4));
    }

    public static ReservationRequestDTO toReservationRequestDTOWithInvalidData() {
        return new ReservationRequestDTO(LocalDate.now().plusDays(5), LocalDate.now());
    }

    public static ReservationEntity toReservationEntityTest(CustomerEntity customer, RoomEntity room) {
        return new ReservationEntityBuilder()
                .withCustomer(customer)
                .withRoom(room)
                .withCheckIn(LocalDate.now().plusDays(1))
                .withCheckOut(LocalDate.now().plusDays(5))
                .withStatus(ReservationStatus.SCHEDULED)
                .build();
    }

    public static ReservationEntity toReservationIN_USE(CustomerEntity customer, RoomEntity room) {
        return new ReservationEntityBuilder()
                .withCustomer(customer)
                .withRoom(room)
                .withCheckIn(LocalDate.now().minusDays(1))
                .withCheckOut(LocalDate.now().plusDays(4))
                .withStatus(ReservationStatus.IN_USE)
                .build();
    }

    public static ReservationRequestDTO toReservationRequestDTOWithMethodArgumentNotValid() {
        return new ReservationRequestDTO(LocalDate.now().minusDays(5), LocalDate.now());
    }
}
