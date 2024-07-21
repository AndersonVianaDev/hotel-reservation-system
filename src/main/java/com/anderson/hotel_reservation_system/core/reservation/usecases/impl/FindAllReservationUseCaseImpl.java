package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.FindAllReservationsUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FindAllReservationUseCaseImpl implements FindAllReservationsUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(FindAllReservationUseCaseImpl.class);

    private final ReservationRepository repository;

    public FindAllReservationUseCaseImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Reservation> execute() {
        log.debug("Executing Find all reservations use case to retrieve all reservations from the repository.");
        return repository.findAll();
    }
}
