package com.anderson.hotel_reservation_system.core.reservation.usecases.ports;

import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.DateRangeDTO;

import java.util.List;

public interface FindAllReservationsByDateRangeUseCasePort {
    List<Reservation> execute(DateRangeDTO dto);
}
