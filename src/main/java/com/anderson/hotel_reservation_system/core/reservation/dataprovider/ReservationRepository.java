package com.anderson.hotel_reservation_system.core.reservation.dataprovider;

import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(UUID id);
    List<Reservation> findAllByDateRange(LocalDate checkIn, LocalDate checkOut);
}
