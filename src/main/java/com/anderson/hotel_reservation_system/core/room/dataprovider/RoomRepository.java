package com.anderson.hotel_reservation_system.core.room.dataprovider;

import com.anderson.hotel_reservation_system.core.room.domain.Room;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository {
    Room save(Room room);
    Optional<Room> findByRoomNumber(String roomNumber);
    Optional<Room> findById(UUID id);
}
