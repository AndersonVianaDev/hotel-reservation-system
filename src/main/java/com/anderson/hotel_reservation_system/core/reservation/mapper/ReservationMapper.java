package com.anderson.hotel_reservation_system.core.reservation.mapper;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.reservation.builder.ReservationBuilder;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.ReservationDTO;
import com.anderson.hotel_reservation_system.core.reservation.enums.ReservationStatus;
import com.anderson.hotel_reservation_system.core.room.domain.Room;

public class ReservationMapper {

    public static Reservation toReservation(ReservationDTO dto, Customer customer, Room room) {
        return new ReservationBuilder()
                .withCheckIn(dto.checkIn())
                .withCheckOut(dto.checkOut())
                .withStatus(ReservationStatus.SCHEDULED)
                .withCustomer(customer)
                .withRoom(room)
                .build();
    }
}
