package com.anderson.hotel_reservation_system.core.room.usecases.ports;

import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;

public interface RegisterRoomUseCasePort {
    Room execute(RoomDTO dto);
}
