package com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.impl;

import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.port.SpringCustomerRepository;
import com.anderson.hotel_reservation_system.dataprovider.reservation.entity.ReservationEntity;
import com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.port.SpringReservationRepository;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.port.SpringRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.*;
import static com.anderson.hotel_reservation_system.dataprovider.reservation.mapper.ReservationEntityMapper.*;

@Component
public class ReservationRepositoryImpl implements ReservationRepository {

    @Autowired
    private SpringReservationRepository repository;

    @Autowired
    private SpringCustomerRepository customerRepository;

    @Autowired
    private SpringRoomRepository roomRepository;

    @Override
    public Reservation save(Reservation reservation) {
        UUID idCustomer = reservation.getCustomer().getId();
        UUID idRoom = reservation.getRoom().getId();
        CustomerEntity customerEntity = customerRepository.findById(idCustomer).orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
        RoomEntity roomEntity = roomRepository.findById(idRoom).orElseThrow(() -> new NotFoundException(ROOM_NOT_FOUND));

        ReservationEntity reservationEntity = toReservationEntity(reservation, customerEntity, roomEntity);

        return toReservation(repository.save(reservationEntity));
    }

    @Override
    public Optional<Reservation> findById(UUID id) {
        Optional<ReservationEntity> reservationEntity = repository.findById(id);
        return toOptionalReservation(reservationEntity);
    }

    @Override
    public List<Reservation> findAllByDateRange(LocalDate startDate, LocalDate endDate) {
        List<ReservationEntity> reservationEntities = repository.findAllByDateRange(startDate, endDate);
        return toReservationList(reservationEntities);
    }

    @Override
    public Reservation update(Reservation reservation) {
        ReservationEntity reservationEntity = repository.findById(reservation.getId()).orElseThrow(() -> new NotFoundException(RESERVATION_NOT_FOUND));
        reservationEntity.setStatus(reservation.getStatus());
        return toReservation(repository.save(reservationEntity));
    }

    @Override
    public List<Reservation> findAll() {
        return toReservationList(repository.findAll());
    }
}
