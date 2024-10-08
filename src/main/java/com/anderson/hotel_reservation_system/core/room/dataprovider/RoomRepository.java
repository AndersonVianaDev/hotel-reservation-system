package com.anderson.hotel_reservation_system.core.room.dataprovider;

import com.anderson.hotel_reservation_system.core.room.domain.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository {
    Room save(Room room);
    Optional<Room> findByRoomNumber(String roomNumber);
    Optional<Room> findById(UUID id);
    void delete(Room room);
    List<Room> findAllOccupiedRooms();
    Room update(Room room);
    List<Room> findAll();
    List<Room> findAllAvailableRooms(LocalDate startDate, LocalDate endDate);
}
