package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.anderson.hotel_reservation_system.core.room.builder.RoomBuilderTest.toRoomListSize2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllOccupiedRoomsUseCaseImplTest {

    @InjectMocks
    private FindAllOccupiedRoomsUseCaseImpl useCase;

    @Mock
    private RoomRepository repository;

    @Test
    @DisplayName("find all rooms occupied successfully")
    void execute() {
        List<Room> rooms = toRoomListSize2();

        when(repository.findAllOccupiedRooms()).thenReturn(rooms);

        List<Room> roomsListResult = useCase.execute();

        assertEquals(rooms, roomsListResult);
    }
}