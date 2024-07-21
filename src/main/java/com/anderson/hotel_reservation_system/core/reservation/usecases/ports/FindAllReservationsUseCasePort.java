package com.anderson.hotel_reservation_system.core.reservation.usecases.ports;

import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;

import java.util.List;

public interface FindAllReservationsUseCasePort {
    List<Reservation> execute();
}
