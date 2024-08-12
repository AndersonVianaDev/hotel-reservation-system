package com.anderson.hotel_reservation_system.core.room.usecases.ports;

import com.anderson.hotel_reservation_system.core.room.domain.Room;

import java.time.LocalDate;
import java.util.List;

public interface FindAllAvailableRoomsUseCasePort {
    List<Room> execute(LocalDate startDate, LocalDate endDate);
}
