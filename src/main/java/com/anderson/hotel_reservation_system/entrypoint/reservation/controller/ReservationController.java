package com.anderson.hotel_reservation_system.entrypoint.reservation.controller;

import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.DateRangeDTO;
import com.anderson.hotel_reservation_system.core.reservation.dtos.ReservationDTO;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.FindAllReservationsByDateRangeUseCasePort;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.FindReservationByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.RegisterReservationUseCasePort;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.UpdateStatusReservationUseCasePort;
import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.DateRangeRequestDTO;
import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.ReservationRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.reservation.mapper.ReservationRequestDTOMapper.toDateRangeDTO;
import static com.anderson.hotel_reservation_system.entrypoint.reservation.mapper.ReservationRequestDTOMapper.toReservationDTO;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    @Autowired
    private RegisterReservationUseCasePort registerReservation;

    @Autowired
    private FindReservationByIdUseCasePort findReservationById;

    @Autowired
    private FindAllReservationsByDateRangeUseCasePort findAllReservationsByDateRange;

    @Autowired
    private UpdateStatusReservationUseCasePort updateStatusReservation;

    @PostMapping("/register")
    public ResponseEntity<Reservation> register(@PathVariable("idCustomer") UUID idCustomer, @PathVariable("idRoom") UUID idRoom, @Valid @RequestBody ReservationRequestDTO dto) {
        ReservationDTO reservationDTO = toReservationDTO(idCustomer, idRoom, dto);
        Reservation reservation = registerReservation.execute(reservationDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reservation.getId())
                .toUri())
                .body(reservation);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Reservation> get(@PathVariable("id") UUID id) {
        Reservation reservation = findReservationById.execute(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Reservation>> getAllByDateRange(@ModelAttribute DateRangeRequestDTO dto) {
        DateRangeDTO dateRangeDTO = toDateRangeDTO(dto);
        List<Reservation> reservations = findAllReservationsByDateRange.execute(dateRangeDTO);
        return ResponseEntity.ok(reservations);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Reservation> patch(@PathVariable("id") UUID id, @RequestParam("status") String status) {
        Reservation reservation = updateStatusReservation.execute(id, status);
        return ResponseEntity.ok(reservation);
    }
}
