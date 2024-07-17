package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.UpdateRoomUseCasePort;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.nonNull;

public class UpdateRoomUseCaseImpl implements UpdateRoomUseCasePort {

    private final RoomRepository repository;

    private final FindRoomByIdUseCasePort findRoomById;

    public UpdateRoomUseCaseImpl(RoomRepository repository, FindRoomByIdUseCasePort findRoomById) {
        this.repository = repository;
        this.findRoomById = findRoomById;
    }

    @Override
    public Room execute(UUID id, RoomDTO dto) {
        Room room = findRoomById.execute(id);

        if(nonNull(dto.roomNumber()) && !Objects.equals(dto.roomNumber(), room.getRoomNumber())) {
            room.setRoomNumber(dto.roomNumber());
        }
        if(nonNull(dto.type()) && !Objects.equals(dto.type(), room.getType())) {
            room.setType(dto.type());
        }
        if(nonNull(dto.price()) && !Objects.equals(dto.price(), room.getPrice())) {
            room.setPrice(dto.price());
        }

        return repository.save(room);
    }
}
