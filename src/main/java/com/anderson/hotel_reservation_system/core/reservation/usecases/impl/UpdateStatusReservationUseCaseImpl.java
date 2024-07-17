package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.enums.ReservationStatus;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.FindReservationByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.UpdateStatusReservationUseCasePort;

import java.util.UUID;

public class UpdateStatusReservationUseCaseImpl implements UpdateStatusReservationUseCasePort {

    private final ReservationRepository repository;

    private final FindReservationByIdUseCasePort findReservationById;

    public UpdateStatusReservationUseCaseImpl(ReservationRepository repository, FindReservationByIdUseCasePort findReservationById) {
        this.repository = repository;
        this.findReservationById = findReservationById;
    }

    @Override
    public Reservation execute(UUID id, String status) {
        Reservation reservation = findReservationById.execute(id);
        reservation.setStatus(ReservationStatus.fromString(status));

        return repository.save(reservation);
    }
}
