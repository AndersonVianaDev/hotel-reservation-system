package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.RESERVATION_NOT_FOUND;
import static com.anderson.hotel_reservation_system.core.reservation.builder.ReservationBuilderTest.toReservation;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindReservationByIdUseCaseImplTest {

    @InjectMocks
    private FindReservationByIdUseCaseImpl useCase;

    @Mock
    private ReservationRepository repository;

    @Test
    @DisplayName("successfully find reservation by ID")
    void execute() {
        Reservation reservation = toReservation();
        UUID id = reservation.getId();

        when(repository.findById(id)).thenReturn(Optional.of(reservation));

        Reservation reservationResult = useCase.execute(id);

        assertEquals(reservation, reservationResult);
    }

    @Test
    @DisplayName("reservation not found")
    void executeWithReservationNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> useCase.execute(id));

        assertEquals(RESERVATION_NOT_FOUND, exception.getMessage());
    }
}