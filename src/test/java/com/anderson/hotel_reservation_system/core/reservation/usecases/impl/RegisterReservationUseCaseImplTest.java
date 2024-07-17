package com.anderson.hotel_reservation_system.core.reservation.usecases.impl;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.exceptions.InvalidDataException;
import com.anderson.hotel_reservation_system.core.reservation.dataprovider.ReservationRepository;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.ReservationDTO;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.FindRoomByIdUseCasePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.customer.builder.CustomerBuilderTest.toCustomer;
import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.CHECK_OUT_BEFORE_CHECK_IN;
import static com.anderson.hotel_reservation_system.core.reservation.builder.ReservationBuilderTest.toReservationDTO;
import static com.anderson.hotel_reservation_system.core.room.builder.RoomBuilderTest.toRoom;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterReservationUseCaseImplTest {

    @InjectMocks
    private RegisterReservationUseCaseImpl useCase;

    @Mock
    private ReservationRepository repository;

    @Mock
    private FindRoomByIdUseCasePort findRoomById;

    @Mock
    private FindCustomerByIdUseCasePort findCustomerById;

    @Test
    @DisplayName("successfully registering reservation")
    void execute() {
        ReservationDTO reservationDTO = toReservationDTO();
        Customer customer = toCustomer();
        Room room = toRoom();

        when(findCustomerById.execute(reservationDTO.idCustomer())).thenReturn(customer);
        when(findRoomById.execute(reservationDTO.idRoom())).thenReturn(room);
        when(repository.save(any(Reservation.class))).thenAnswer(invocations -> invocations.getArgument(0));

        Reservation reservation = useCase.execute(reservationDTO);

        assertEquals(reservationDTO.checkIn(), reservation.getCheckIn());
        assertEquals(reservationDTO.checkOut(), reservation.getCheckOut());
        assertEquals(customer, reservation.getCustomer());
        assertEquals(room, reservation.getRoom());
    }

    @Test
    @DisplayName("check-out date must be after check-in date")
    void executeWithInvalidData() {
        ReservationDTO reservationDTO = new ReservationDTO(UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDate.of(2024,7,20),
                LocalDate.of(2024, 7, 17));

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> useCase.execute(reservationDTO));

        assertEquals(CHECK_OUT_BEFORE_CHECK_IN, exception.getMessage());
    }
}