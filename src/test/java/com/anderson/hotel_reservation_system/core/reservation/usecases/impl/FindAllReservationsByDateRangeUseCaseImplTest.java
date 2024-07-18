package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.exceptions.InvalidDataException;
import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.DateRangeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.CHECK_OUT_BEFORE_CHECK_IN;
import static com.anderson.hotel_reservation_system.core.reservation.builder.ReservationBuilderTest.toDateRangeDTO;
import static com.anderson.hotel_reservation_system.core.reservation.builder.ReservationBuilderTest.toReservationListSize2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllReservationsByDateRangeUseCaseImplTest {

    @InjectMocks
    private FindAllReservationsByDateRangeUseCaseImpl useCase;

    @Mock
    private ReservationRepository repository;

    @Test
    @DisplayName("find all reservations by date range successfully")
    void execute() {
        List<Reservation> reservations = toReservationListSize2();
        DateRangeDTO dto = toDateRangeDTO();

        when(repository.findAllByDateRange(dto.startDate(), dto.endDate())).thenReturn(reservations);

        List<Reservation> reservationsListResul = useCase.execute(dto);

        assertEquals(reservations, reservationsListResul);
    }

    @Test
    @DisplayName("check-out date must be after check-in date")
    void executeWithInvalidData() {
        DateRangeDTO dto = new DateRangeDTO(LocalDate.of(2024, 7, 17), LocalDate.of(2024, 7, 12));

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> useCase.execute(dto));

        assertEquals(CHECK_OUT_BEFORE_CHECK_IN, exception.getMessage());
    }
}