package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindAllAvailableRoomsUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class FindAllAvailableRoomsUseCaseImpl implements FindAllAvailableRoomsUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(FindAllAvailableRoomsUseCaseImpl.class);

    private final RoomRepository repository;

    public FindAllAvailableRoomsUseCaseImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Room> execute(LocalDate startDate, LocalDate endDate) {
        log.debug("Find all occupied rooms use case started");
        return repository.findAllAvailableRooms(startDate, endDate);
    }
}
