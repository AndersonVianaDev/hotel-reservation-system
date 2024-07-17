package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.FindReservationByIdUseCasePort;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.RESERVATION_NOT_FOUND;

public class FindReservationByIdUseCaseImpl implements FindReservationByIdUseCasePort {

    private final ReservationRepository repository;

    public FindReservationByIdUseCaseImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reservation execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(RESERVATION_NOT_FOUND));
    }
}
