package com.anderson.hotel_reservation_system.core.reservation.usecases.ports;

import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;

import java.util.UUID;

public interface FindReservationByIdUseCasePort {
    Reservation execute(UUID id);
}
