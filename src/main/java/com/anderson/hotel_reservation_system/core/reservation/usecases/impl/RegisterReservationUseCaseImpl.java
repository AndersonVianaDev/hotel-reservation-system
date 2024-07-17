package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.ReservationDTO;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.RegisterReservationUseCasePort;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;

import static com.anderson.hotel_reservation_system.core.reservation.mapper.ReservationMapper.toReservation;

public class RegisterReservationUseCaseImpl implements RegisterReservationUseCasePort {

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
        Customer customer = findCustomerById.execute(dto.idCustomer());
        Room room = findRoomById.execute(dto.idRoom());

        Reservation reservation = toReservation(dto, customer, room);

        return repository.save(reservation);
    }
}
