package com.anderson.hotel_reservation_system.dataprovider.room.mapper;

import com.anderson.hotel_reservation_system.core.room.builder.RoomBuilder;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.dataprovider.room.builder.RoomEntityBuilder;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;

import java.util.List;
import java.util.Optional;

public class RoomEntityMapper {
    public static RoomEntity toRoomEntity(Room room) {
        return new RoomEntityBuilder()
                .withRoomNumber(room.getRoomNumber())
                .withType(room.getType())
                .withPrice(room.getPrice())
                .build();
    }

    public static Room toRoom(RoomEntity roomEntity) {
        return new RoomBuilder()
                .withId(roomEntity.getId())
                .withRoomNumber(roomEntity.getRoomNumber())
                .withType(roomEntity.getType())
                .withPrice(roomEntity.getPrice())
                .build();
    }

    public static Optional<Room> toOptionalRoom(Optional<RoomEntity> roomEntity) {
        return roomEntity.map(RoomEntityMapper::toRoom);
    }

    public static List<Room> toRoomList(List<RoomEntity> roomEntities) {
        return roomEntities.stream().map(RoomEntityMapper::toRoom).toList();
    }
}
