package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.DeleteRoomUseCasePort;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class DeleteRoomUseCaseImpl implements DeleteRoomUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(DeleteRoomUseCaseImpl.class);
    private final RoomRepository repository;

    private final FindRoomByIdUseCasePort findRoomById;

    public DeleteRoomUseCaseImpl(RoomRepository repository, FindRoomByIdUseCasePort findRoomById) {
        this.repository = repository;
        this.findRoomById = findRoomById;
    }

    @Override
    public void execute(UUID id) {
        log.debug("Delete room use case started for room id: {}", id);
        Room room = findRoomById.execute(id);
        repository.delete(room);
    }
}
