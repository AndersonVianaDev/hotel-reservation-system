package com.anderson.hotel_reservation_system.entrypoint.reservation.mapper;

import com.anderson.hotel_reservation_system.core.reservation.dtos.DateRangeDTO;
import com.anderson.hotel_reservation_system.core.reservation.dtos.ReservationDTO;
import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.DateRangeRequestDTO;
import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.ReservationRequestDTO;

import java.util.UUID;

public class ReservationRequestDTOMapper {
    public static ReservationDTO toReservationDTO(UUID idCustomer, UUID idRoom, ReservationRequestDTO dto) {
        return new ReservationDTO(idCustomer, idRoom, dto.checkIn(), dto.checkOut());
    }

    public static DateRangeDTO toDateRangeDTO(DateRangeRequestDTO dto) {
        return new DateRangeDTO(dto.startDate(), dto.endDate());
    }
}
