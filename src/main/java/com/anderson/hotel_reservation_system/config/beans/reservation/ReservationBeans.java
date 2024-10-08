package com.anderson.hotel_reservation_system.config.beans.reservation;

import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.reservation.usecases.impl.*;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.*;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;
import com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.impl.ReservationRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationBeans {

    @Bean
    public RegisterReservationUseCasePort registerReservationUseCasePort(ReservationRepositoryImpl repository, FindCustomerByIdUseCasePort findCustomerById, FindRoomByIdUseCasePort findRoomById) {
        return new RegisterReservationUseCaseImpl(repository, findCustomerById, findRoomById);
    }

    @Bean
    public FindReservationByIdUseCasePort findReservationByIdUseCasePort(ReservationRepositoryImpl repository) {
        return new FindReservationByIdUseCaseImpl(repository);
    }

    @Bean
    public FindAllReservationsByDateRangeUseCasePort findAllReservationsByDateRangeUseCasePort(ReservationRepositoryImpl repository) {
        return new FindAllReservationsByDateRangeUseCaseImpl(repository);
    }

    @Bean
    public UpdateStatusReservationUseCasePort updateStatusReservationUseCasePort(FindReservationByIdUseCasePort findReservationById, ReservationRepositoryImpl repository) {
        return new UpdateStatusReservationUseCaseImpl(repository, findReservationById);
    }

    @Bean
    public FindAllReservationsUseCasePort findAllReservationsUseCasePort(ReservationRepositoryImpl repository) {
        return new FindAllReservationUseCaseImpl(repository);
    }
}
