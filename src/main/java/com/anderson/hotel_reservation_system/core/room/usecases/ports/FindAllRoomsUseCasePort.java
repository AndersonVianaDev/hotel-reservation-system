package com.anderson.hotel_reservation_system.core.room.usecases.ports;

import com.anderson.hotel_reservation_system.core.room.domain.Room;

import java.util.List;

public interface FindAllRoomsUseCasePort {
    List<Room> execute();
}
