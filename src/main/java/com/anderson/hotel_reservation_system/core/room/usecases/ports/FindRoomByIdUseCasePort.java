package com.anderson.hotel_reservation_system.core.room.usecases.ports;

import com.anderson.hotel_reservation_system.core.room.domain.Room;

import java.util.UUID;

public interface FindRoomByIdUseCasePort {
    Room execute(UUID id);
}
