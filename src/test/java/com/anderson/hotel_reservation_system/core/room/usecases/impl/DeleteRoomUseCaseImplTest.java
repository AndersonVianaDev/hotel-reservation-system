package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.room.builder.RoomBuilderTest.toRoom;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteRoomUseCaseImplTest {

    @InjectMocks
    private DeleteRoomUseCaseImpl useCase;

    @Mock
    private RoomRepository repository;

    @Mock
    private FindRoomByIdUseCasePort findRoomById;

    @Test
    @DisplayName("successfully deleting room")
    void execute() {
        Room room = toRoom();
        UUID id = room.getId();

        when(findRoomById.execute(id)).thenReturn(room);

        useCase.execute(id);

        verify(repository, times(1)).delete(room);
    }
}