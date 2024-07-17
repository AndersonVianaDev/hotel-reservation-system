package com.anderson.hotel_reservation_system.core.room.builder;

import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class RoomBuilderTest {
    public static RoomDTO toRoomDTO() {
        return new RoomDTO("A12", "suite", new BigDecimal(355));
    }

    public static Room toRoom() {
        return new RoomBuilder()
                .withId(UUID.randomUUID())
                .withRoomNumber("A12")
                .withType("suite")
                .withPrice(new BigDecimal(355))
                .build();
    }

    public static List<Room> toRoomListSize2() {
        return List.of( new RoomBuilder()
                .withId(UUID.randomUUID())
                .withRoomNumber("A12")
                .withType("suite")
                .withPrice(new BigDecimal(355))
                .build(),
                new RoomBuilder()
                        .withId(UUID.randomUUID())
                        .withRoomNumber("A13")
                        .withType("suite")
                        .withPrice(new BigDecimal(355))
                        .build()
        );
    }
}
