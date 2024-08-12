package com.anderson.hotel_reservation_system.entrypoint.room.builders;

import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.dataprovider.room.builder.RoomEntityBuilder;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;
import com.anderson.hotel_reservation_system.entrypoint.room.dtos.RoomRequestDTO;

import java.math.BigDecimal;
import java.util.List;

public class RoomBuilderTest {

    public static RoomDTO toRoomDTO() {
        return new RoomDTO("A12", "double", new BigDecimal(355));
    }

    public static RoomEntity toRoomEntity1() {
        return new RoomEntityBuilder()
                .withRoomNumber("A12")
                .withType("double")
                .withPrice(new BigDecimal(355))
                .build();
    }

    public static RoomEntity toRoomEntity2() {
        return new RoomEntityBuilder()
                .withRoomNumber("A13")
                .withType("double")
                .withPrice(new BigDecimal(355))
                .build();
    }

    public static RoomEntity toRoomEntity3() {
        return new RoomEntityBuilder()
                .withRoomNumber("A13")
                .withType("double")
                .withPrice(new BigDecimal(355))
                .build();
    }

    public static List<RoomEntity> toRoomsEntity() {
        return List.of(toRoomEntity1(), toRoomEntity2(), toRoomEntity3());
    }

    public static RoomRequestDTO toRoomRequestDTOWithMethodArgumentNotValid() {
        return new RoomRequestDTO("A1234546", "double", new BigDecimal(333));
    }
}
