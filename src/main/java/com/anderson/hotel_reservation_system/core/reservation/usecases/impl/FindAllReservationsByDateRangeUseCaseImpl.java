package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.InvalidDataException;
import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.DateRangeDTO;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.FindAllReservationsByDateRangeUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.CHECK_OUT_BEFORE_CHECK_IN;

public class FindAllReservationsByDateRangeUseCaseImpl implements FindAllReservationsByDateRangeUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(FindAllReservationsByDateRangeUseCaseImpl.class);

    private final ReservationRepository repository;

    public FindAllReservationsByDateRangeUseCaseImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Reservation> execute(DateRangeDTO dto) {
        log.debug("Attempting to find reservations within date range: {} to {}", dto.startDate(), dto.endDate());
        if(!dto.startDate().isBefore(dto.endDate())) throw new InvalidDataException(CHECK_OUT_BEFORE_CHECK_IN);

        return repository.findAllByDateRange(dto.startDate(), dto.endDate());
    }
}
