package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.DataConflictException;
import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.RegisterRoomUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.NUMBER_ROOM_ALREADY_REGISTERED;
import static com.anderson.hotel_reservation_system.core.room.mapper.RoomMapper.toRoom;

public class RegisterRoomUseCaseImpl implements RegisterRoomUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(RegisterRoomUseCaseImpl.class);

    private final RoomRepository repository;

    public RegisterRoomUseCaseImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public Room execute(RoomDTO dto) {
        log.debug("Register room use case started with room details: {}", dto);
        if(repository.findByRoomNumber(dto.roomNumber()).isPresent()) throw new DataConflictException(NUMBER_ROOM_ALREADY_REGISTERED);

        Room room = toRoom(dto);
        return repository.save(room);
    }
}
