package com.anderson.hotel_reservation_system.entrypoint.room.builders;

import com.anderson.hotel_reservation_system.core.room.builder.RoomBuilder;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;

import java.math.BigDecimal;
import java.util.List;

import static com.anderson.hotel_reservation_system.dataprovider.room.mapper.RoomEntityMapper.toRoomEntity;

public class RoomBuilderTest {

    public static RoomDTO toRoomDTO() {
        return new RoomDTO("A12", "double", new BigDecimal(355));
    }

    public static Room toRoom1() {
        return new RoomBuilder()
                .withRoomNumber("A12")
                .withType("double")
                .withPrice(new BigDecimal(355))
                .build();
    }

    public static Room toRoom2() {
        return new RoomBuilder()
                .withRoomNumber("A13")
                .withType("double")
                .withPrice(new BigDecimal(355))
                .build();
    }

    public static Room toRoom3() {
        return new RoomBuilder()
                .withRoomNumber("A13")
                .withType("double")
                .withPrice(new BigDecimal(355))
                .build();
    }

    public static List<RoomEntity> toRoomsEntity() {
        return List.of(toRoomEntity(toRoom1()), toRoomEntity(toRoom2()), toRoomEntity(toRoom3()));
    }
}
