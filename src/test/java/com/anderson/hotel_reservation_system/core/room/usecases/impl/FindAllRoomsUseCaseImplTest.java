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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllRoomsUseCaseImplTest {

    @InjectMocks
    private FindAllRoomsUseCaseImpl useCase;

    @Mock
    private RoomRepository repository;

    @Test
    @DisplayName("find all rooms successfully")
    void execute() {
        List<Room> roomList = toRoomListSize2();

        when(repository.findAll()).thenReturn(roomList);

        List<Room> roomListResult = useCase.execute();

        assertEquals(roomList, roomListResult);
    }
}