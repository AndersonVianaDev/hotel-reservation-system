package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static com.anderson.hotel_reservation_system.core.room.builder.RoomBuilderTest.toRoomListSize2;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllAvailableRoomsUseCaseImplTest {

    @InjectMocks
    private FindAllAvailableRoomsUseCaseImpl useCase;

    @Mock
    private RoomRepository repository;

    @Test
    @DisplayName("find all available rooms successfully")
    void execute() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(10);
        List<Room> rooms = toRoomListSize2();

        when(repository.findAllAvailableRooms(startDate, endDate)).thenReturn(rooms);

        List<Room> roomsResult = useCase.execute(startDate, endDate);

        assertEquals(rooms, roomsResult);
    }
}