package com.anderson.hotel_reservation_system.core.reservation.usecases.ports;

import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.ReservationDTO;

public interface RegisterReservationUseCasePort {
    Reservation execute(ReservationDTO dto);
}
