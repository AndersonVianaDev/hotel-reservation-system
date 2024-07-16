package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.DataConflictException;
import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.RegisterRoomUseCasePort;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.NUMBER_ROOM_ALREADY_REGISTERED;
import static com.anderson.hotel_reservation_system.core.room.mapper.RoomMapper.toRoom;

public class RegisterRoomUseCaseImpl implements RegisterRoomUseCasePort {

    private final RoomRepository repository;

    public RegisterRoomUseCaseImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public Room execute(RoomDTO dto) {
        if(repository.findByRoomNumber(dto.roomNumber()).isPresent()) throw new DataConflictException(NUMBER_ROOM_ALREADY_REGISTERED);
        Room room = toRoom(dto);
        return repository.save(room);
    }
}
