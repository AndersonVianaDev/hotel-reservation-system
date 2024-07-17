package com.anderson.hotel_reservation_system.core.room.usecases.ports;

import java.util.UUID;

public interface DeleteRoomUseCasePort {
    void execute(UUID id);
}
