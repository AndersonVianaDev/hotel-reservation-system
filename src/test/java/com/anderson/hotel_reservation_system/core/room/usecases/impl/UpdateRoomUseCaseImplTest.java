package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.room.builder.RoomBuilderTest.toRoom;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateRoomUseCaseImplTest {

    @InjectMocks
    private UpdateRoomUseCaseImpl useCase;

    @Mock
    private FindRoomByIdUseCasePort findRoomById;

    @Mock
    private RoomRepository repository;

    @Test
    @DisplayName("successfully updating the room")
    void execute() {
        Room room = toRoom();
        UUID id = room.getId();
        RoomDTO dto = new RoomDTO("A13", "normal", new BigDecimal(135));

        when(findRoomById.execute(id)).thenReturn(room);
        when(repository.update(any(Room.class))).thenAnswer(invocations -> invocations.getArgument(0));

        Room roomResult = useCase.execute(id, dto);

        assertEquals(dto.roomNumber(), roomResult.getRoomNumber());
        assertEquals(dto.type(), roomResult.getType());
        assertEquals(dto.price(), roomResult.getPrice());
    }
}