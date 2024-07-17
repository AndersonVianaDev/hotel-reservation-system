package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindAllOccupiedRoomsUseCasePort;

import java.util.List;

public class FindAllOccupiedRoomsUseCaseImpl implements FindAllOccupiedRoomsUseCasePort {

    private final RoomRepository repository;

    public FindAllOccupiedRoomsUseCaseImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Room> execute() {
        return repository.findAllOccupiedRooms();
    }
}
