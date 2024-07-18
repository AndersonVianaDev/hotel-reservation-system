package com.anderson.hotel_reservation_system.entrypoint.room.mapper;

import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.entrypoint.room.dtos.RoomRequestDTO;

public class RoomRequestDTOMapper {
    public static RoomDTO toRoomDTO(RoomRequestDTO dto) {
        return new RoomDTO(dto.roomNumber(), dto.type(), dto.price());
    }
}
