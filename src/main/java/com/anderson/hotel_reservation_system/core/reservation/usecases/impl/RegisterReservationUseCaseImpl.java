package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.exceptions.DataConflictException;
import com.anderson.hotel_reservation_system.core.exceptions.InvalidDataException;
import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.ReservationDTO;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.RegisterReservationUseCasePort;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.CHECK_OUT_BEFORE_CHECK_IN;
import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.RESERVATION_CONFLICT;
import static com.anderson.hotel_reservation_system.core.reservation.mapper.ReservationMapper.toReservation;

public class RegisterReservationUseCaseImpl implements RegisterReservationUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(RegisterReservationUseCaseImpl.class);

    private final ReservationRepository repository;

    private final FindCustomerByIdUseCasePort findCustomerById;

    private final FindRoomByIdUseCasePort findRoomById;

    public RegisterReservationUseCaseImpl(ReservationRepository repository, FindCustomerByIdUseCasePort findCustomerById, FindRoomByIdUseCasePort findRoomById) {
        this.repository = repository;
        this.findCustomerById = findCustomerById;
        this.findRoomById = findRoomById;
    }

    @Override
    public Reservation execute(ReservationDTO dto) {
        log.debug("Register reservation use case started with ReservationDTO: {}", dto);
        if(!dto.checkIn().isBefore(dto.checkOut())) throw new InvalidDataException(CHECK_OUT_BEFORE_CHECK_IN);

        log.debug("Retrieving customer with id: {}", dto.idCustomer());
        Customer customer = findCustomerById.execute(dto.idCustomer());

        log.debug("Retrieving room with id: {}", dto.idRoom());
        Room room = findRoomById.execute(dto.idRoom());

        if(repository.findReservationByRoomIdAndReservationDate(room.getId(), dto.checkIn(), dto.checkOut()).isPresent()) {
            throw new DataConflictException(RESERVATION_CONFLICT);
        }

        Reservation reservation = toReservation(dto, customer, room);

        return repository.save(reservation);
    }
}
