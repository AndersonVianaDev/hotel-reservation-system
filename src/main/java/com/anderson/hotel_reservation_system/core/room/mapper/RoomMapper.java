package com.anderson.hotel_reservation_system.core.room.mapper;

import com.anderson.hotel_reservation_system.core.room.builder.RoomBuilder;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;

public class RoomMapper {
    public static Room toRoom(RoomDTO dto) {
        return new RoomBuilder()
                .withRoomNumber(dto.roomNumber())
                .withType(dto.type())
                .withPrice(dto.price())
                .build();
    }
}
