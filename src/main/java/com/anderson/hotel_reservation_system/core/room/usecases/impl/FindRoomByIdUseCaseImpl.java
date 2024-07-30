package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.ROOM_NOT_FOUND;

public class FindRoomByIdUseCaseImpl implements FindRoomByIdUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(FindRoomByIdUseCaseImpl.class);

    private final RoomRepository repository;

    public FindRoomByIdUseCaseImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public Room execute(UUID id) {
        log.debug("Find room use case started for room id: {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException(ROOM_NOT_FOUND));
    }
}
