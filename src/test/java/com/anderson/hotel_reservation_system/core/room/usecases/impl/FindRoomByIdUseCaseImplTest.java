package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.ROOM_NOT_FOUND;
import static com.anderson.hotel_reservation_system.core.room.builder.RoomBuilderTest.toRoom;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindRoomByIdUseCaseImplTest {

    @InjectMocks
    private FindRoomByIdUseCaseImpl useCase;

    @Mock
    private RoomRepository repository;

    @Test
    @DisplayName("successfully find room by ID")
    void execute() {
        Room room = toRoom();
        UUID id = room.getId();

        when(repository.findById(id)).thenReturn(Optional.of(room));

        Room roomResult = useCase.execute(id);

        assertEquals(room, roomResult);
    }

    @Test
    @DisplayName("room not found")
    void executeWithRoomNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> useCase.execute(id));

        assertEquals(ROOM_NOT_FOUND, exception.getMessage());
    }
}