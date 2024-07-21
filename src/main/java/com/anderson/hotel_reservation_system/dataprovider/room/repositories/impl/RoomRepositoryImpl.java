package com.anderson.hotel_reservation_system.dataprovider.room.repositories.impl;

import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.port.SpringRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.ROOM_NOT_FOUND;
import static com.anderson.hotel_reservation_system.dataprovider.room.mapper.RoomEntityMapper.*;

@Component
public class RoomRepositoryImpl implements RoomRepository {

    @Autowired
    private SpringRoomRepository repository;

    @Override
    public Room save(Room room) {
        RoomEntity roomEntity = toRoomEntity(room);
        return toRoom(repository.save(roomEntity));
    }

    @Override
    public Optional<Room> findByRoomNumber(String roomNumber) {
        Optional<RoomEntity> roomEntity = repository.findByRoomNumber(roomNumber);
        return toOptionalRoom(roomEntity);
    }

    @Override
    public Optional<Room> findById(UUID id) {
        Optional<RoomEntity> roomEntity = repository.findById(id);
        return toOptionalRoom(roomEntity);
    }

    @Override
    public void delete(Room room) {
        RoomEntity roomEntity = repository.findById(room.getId()).orElseThrow(() -> new NotFoundException(ROOM_NOT_FOUND));
        repository.delete(roomEntity);
    }

    @Override
    public List<Room> findAllOccupiedRooms() {
        return toRoomList(repository.findAllOccupiedRooms());
    }

    @Override
    public Room update(Room room) {
        RoomEntity roomEntity = repository.findById(room.getId()).orElseThrow(() -> new NotFoundException(ROOM_NOT_FOUND));
        roomEntity.setRoomNumber(room.getRoomNumber());
        roomEntity.setType(room.getType());
        roomEntity.setPrice(room.getPrice());
        return toRoom(repository.save(roomEntity));
    }

    @Override
    public List<Room> findAll() {
        return toRoomList(repository.findAll());
    }
}
