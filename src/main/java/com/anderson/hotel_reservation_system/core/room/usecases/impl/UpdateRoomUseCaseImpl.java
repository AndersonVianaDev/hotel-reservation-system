package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.UpdateRoomUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.nonNull;

public class UpdateRoomUseCaseImpl implements UpdateRoomUseCasePort {

    private static final Logger log = LoggerFactory.getLogger(UpdateRoomUseCaseImpl.class);

    private final RoomRepository repository;

    private final FindRoomByIdUseCasePort findRoomById;

    public UpdateRoomUseCaseImpl(RoomRepository repository, FindRoomByIdUseCasePort findRoomById) {
        this.repository = repository;
        this.findRoomById = findRoomById;
    }

    @Override
    public Room execute(UUID id, RoomDTO dto) {
        log.debug("UpdateRoom use case started for room id: {}", id);
        Room room = findRoomById.execute(id);

        if(nonNull(dto.roomNumber()) && !Objects.equals(dto.roomNumber(), room.getRoomNumber())) {
            log.debug("Updating room number from {} to {}", room.getRoomNumber(), dto.roomNumber());
            room.setRoomNumber(dto.roomNumber());
        }
        if(nonNull(dto.type()) && !Objects.equals(dto.type(), room.getType())) {
            log.debug("Updating room type from {} to {}", room.getType(), dto.type());
            room.setType(dto.type());
        }
        if(nonNull(dto.price()) && !Objects.equals(dto.price(), room.getPrice())) {
            log.debug("Updating room price from {} to {}", room.getPrice(), dto.price());
            room.setPrice(dto.price());
        }

        return repository.update(room);
    }
}
