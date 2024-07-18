package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.InvalidDataException;
import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.enums.ReservationStatus;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.FindReservationByIdUseCasePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.STATUS_CHANGE_NOT_ALLOWED;
import static com.anderson.hotel_reservation_system.core.reservation.builder.ReservationBuilderTest.toReservation;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateStatusReservationUseCaseImplTest {

    @InjectMocks
    private UpdateStatusReservationUseCaseImpl useCase;

    @Mock
    private FindReservationByIdUseCasePort findReservationById;

    @Mock
    private ReservationRepository repository;

    @Test
    @DisplayName("successfully updating the reservation")
    void execute() {
        Reservation reservation = toReservation();
        UUID id = reservation.getId();
        String status = "in_use";

        when(findReservationById.execute(id)).thenReturn(reservation);
        when(repository.update(any(Reservation.class))).thenAnswer(invocations -> invocations.getArgument(0));

        Reservation reservationResult = useCase.execute(id, status);

        assertEquals(ReservationStatus.fromString(status), reservationResult.getStatus());
    }

    @Test
    @DisplayName("it is not possible to change the status of this reservation")
    void executeWithInvalidData() {
        Reservation reservation = toReservation();
        UUID id = reservation.getId();
        reservation.setStatus(ReservationStatus.FINISHED);
        String status = "in_use";

        when(findReservationById.execute(id)).thenReturn(reservation);

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> useCase.execute(id, status));

        assertEquals(STATUS_CHANGE_NOT_ALLOWED, exception.getMessage());
    }
}