package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.FindReservationByIdUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.RESERVATION_NOT_FOUND;

public class FindReservationByIdUseCaseImpl implements FindReservationByIdUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(FindReservationByIdUseCaseImpl.class);

    private final ReservationRepository repository;

    public FindReservationByIdUseCaseImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reservation execute(UUID id) {
        log.debug("Attempting to find reservation with id: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            log.error("Reservation with id {} not found", id);
            return new NotFoundException(RESERVATION_NOT_FOUND);
        });
    }
}
