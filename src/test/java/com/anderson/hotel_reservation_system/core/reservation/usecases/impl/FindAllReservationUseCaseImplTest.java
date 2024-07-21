package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.anderson.hotel_reservation_system.core.reservation.builder.ReservationBuilderTest.toReservationListSize2;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllReservationUseCaseImplTest {

    @InjectMocks
    private FindAllReservationUseCaseImpl useCase;

    @Mock
    private ReservationRepository repository;

    @Test
    @DisplayName("find all reservations successfully")
    void execute() {
        List<Reservation> reservations = toReservationListSize2();

        when(repository.findAll()).thenReturn(reservations);

        List<Reservation> reservationListResult = useCase.execute();

        assertEquals(reservations, reservationListResult);
    }
}