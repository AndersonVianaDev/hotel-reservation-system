package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindAllOccupiedRoomsUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FindAllOccupiedRoomsUseCaseImpl implements FindAllOccupiedRoomsUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(FindAllOccupiedRoomsUseCaseImpl.class);
    private final RoomRepository repository;

    public FindAllOccupiedRoomsUseCaseImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Room> execute() {
        log.debug("Find all occupied rooms use case started");
        return repository.findAllOccupiedRooms();
    }
}
