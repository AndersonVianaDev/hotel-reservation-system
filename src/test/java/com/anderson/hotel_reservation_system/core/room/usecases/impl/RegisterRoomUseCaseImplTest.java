package com.anderson.hotel_reservation_system.core.room.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.DataConflictException;
import com.anderson.hotel_reservation_system.core.room.dataprovider.RoomRepository;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.NUMBER_ROOM_ALREADY_REGISTERED;
import static com.anderson.hotel_reservation_system.core.room.builder.RoomBuilderTest.toRoom;
import static com.anderson.hotel_reservation_system.core.room.builder.RoomBuilderTest.toRoomDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterRoomUseCaseImplTest {

    @InjectMocks
    private RegisterRoomUseCaseImpl useCase;

    @Mock
    private RoomRepository repository;

    @Test
    @DisplayName("successfully registering room")
    void execute() {
        RoomDTO dto = toRoomDTO();

        when(repository.save(any(Room.class))).thenAnswer(invocations -> invocations.getArgument(0));
        when(repository.findByRoomNumber(dto.roomNumber())).thenReturn(Optional.empty());

        Room roomResult = useCase.execute(dto);

        assertEquals(dto.roomNumber(), roomResult.getRoomNumber());
        assertEquals(dto.type(), roomResult.getType());
        assertEquals(dto.price(), roomResult.getPrice());
    }

    @Test
    @DisplayName("data conflict exception")
    void executeWithDataConflict() {
        RoomDTO dto = toRoomDTO();
        Room room = toRoom();

        when(repository.findByRoomNumber(dto.roomNumber())).thenReturn(Optional.of(room));

        DataConflictException exception = assertThrows(DataConflictException.class, () -> useCase.execute(dto));

        assertEquals(NUMBER_ROOM_ALREADY_REGISTERED, exception.getMessage());
    }
}