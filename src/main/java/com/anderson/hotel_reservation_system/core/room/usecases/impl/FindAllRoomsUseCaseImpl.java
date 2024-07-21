package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindAllRoomsUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FindAllRoomsUseCaseImpl implements FindAllRoomsUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(FindAllRoomsUseCaseImpl.class);

    private final RoomRepository repository;

    public FindAllRoomsUseCaseImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Room> execute() {
        log.debug("Executing Find all rooms use case to retrieve all rooms from the repository.");
        return repository.findAll();
    }
}
