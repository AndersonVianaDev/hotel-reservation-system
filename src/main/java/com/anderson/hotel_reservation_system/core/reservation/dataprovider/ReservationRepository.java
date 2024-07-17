package com.anderson.hotel_reservation_system.core.reservation.dataprovider;

import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
}
