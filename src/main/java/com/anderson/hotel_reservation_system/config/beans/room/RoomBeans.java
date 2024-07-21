package com.anderson.hotel_reservation_system.config.beans.room;

import com.anderson.hotel_reservation_system.core.room.usecases.impl.*;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.*;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.impl.RoomRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomBeans {

    @Bean
    public RegisterRoomUseCasePort registerRoomUseCasePort(RoomRepositoryImpl repository) {
        return new RegisterRoomUseCaseImpl(repository);
    }

    @Bean
    public FindRoomByIdUseCasePort findRoomByIdUseCasePort(RoomRepositoryImpl repository) {
        return new FindRoomByIdUseCaseImpl(repository);
    }

    @Bean
    public FindAllOccupiedRoomsUseCasePort findAllOccupiedRoomsUseCasePort(RoomRepositoryImpl repository) {
        return new FindAllOccupiedRoomsUseCaseImpl(repository);
    }

    @Bean
    public UpdateRoomUseCasePort updateRoomUseCasePort(RoomRepositoryImpl repository, FindRoomByIdUseCasePort findRoomById) {
        return new UpdateRoomUseCaseImpl(repository, findRoomById);
    }

    @Bean
    public DeleteRoomUseCasePort deleteRoomUseCasePort(RoomRepositoryImpl repository, FindRoomByIdUseCasePort findRoomById) {
        return new DeleteRoomUseCaseImpl(repository, findRoomById);
    }

    @Bean
    public FindAllRoomsUseCasePort findAllRoomsUseCasePort(RoomRepositoryImpl repository) {
        return new FindAllRoomsUseCaseImpl(repository);
    }
}
