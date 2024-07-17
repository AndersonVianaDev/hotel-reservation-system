package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.DeleteRoomUseCasePort;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;

import java.util.UUID;

public class DeleteRoomUseCaseImpl implements DeleteRoomUseCasePort {

    private final RoomRepository repository;

    private final FindRoomByIdUseCasePort findRoomById;

    public DeleteRoomUseCaseImpl(RoomRepository repository, FindRoomByIdUseCasePort findRoomById) {
        this.repository = repository;
        this.findRoomById = findRoomById;
    }

    @Override
    public void execute(UUID id) {
        Room room = findRoomById.execute(id);
        repository.delete(room);
    }
}
