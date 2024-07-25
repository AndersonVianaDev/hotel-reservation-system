package com.anderson.hotel_reservation_system.entrypoint.reservation.controller;

import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.dtos.DateRangeDTO;
import com.anderson.hotel_reservation_system.core.reservation.dtos.ReservationDTO;
import com.anderson.hotel_reservation_system.core.reservation.usecases.ports.*;
import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.DateRangeRequestDTO;
import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.ReservationRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Tag(name = "Reservation Controller", description = "Operations related to reservations in the hotel reservation system.")
public class ReservationController {

    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private RegisterReservationUseCasePort registerReservation;

    @Autowired
    private FindReservationByIdUseCasePort findReservationById;

    @Autowired
    private FindAllReservationsByDateRangeUseCasePort findAllReservationsByDateRange;

    @Autowired
    private UpdateStatusReservationUseCasePort updateStatusReservation;

    @Autowired
    private FindAllReservationsUseCasePort findAllReservations;

    @PostMapping("/register/{idCustomer}/{idRoom}")
    @Operation(summary = "Register a new reservation", description = "Register a new reservation with the provided details for a specific customer and room.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reservation created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Reservation> register(@PathVariable("idCustomer") UUID idCustomer, @PathVariable("idRoom") UUID idRoom, @Valid @RequestBody ReservationRequestDTO dto) {
        log.info("Register request received for reservation with customer id: {} and room id: {}", idCustomer, idRoom);
        ReservationDTO reservationDTO = toReservationDTO(idCustomer, idRoom, dto);
        Reservation reservation = registerReservation.execute(reservationDTO);
        log.info("Reservation created successfully with id {}", reservation.getId());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reservation.getId())
                .toUri())
                .body(reservation);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get reservation by ID", description = "Retrieve a reservation by its unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation found and returned"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    public ResponseEntity<Reservation> get(@PathVariable("id") UUID id) {
        log.info("Get request received for reservation with id: {}", id);
        Reservation reservation = findReservationById.execute(id);
        log.info("Reservation retrieved successfully with id: {}", reservation.getId());
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/get")
    @Operation(summary = "Get reservations by date range", description = "Retrieve all reservations within the specified date range.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservations retrieved successfully")
    })
    public ResponseEntity<List<Reservation>> getAllByDateRange(@ModelAttribute DateRangeRequestDTO dto) {
        log.info("Get all reservations by date range request received: {}", dto);
        DateRangeDTO dateRangeDTO = toDateRangeDTO(dto);
        List<Reservation> reservations = findAllReservationsByDateRange.execute(dateRangeDTO);
        log.info("Retrieved {} reservations for date range: {}", reservations.size(), dto);
        return ResponseEntity.ok(reservations);
    }

    @PatchMapping("/patch/{id}")
    @Operation(summary = "Update reservation status", description = "Update the status of an existing reservation using its unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Reservation> patch(@PathVariable("id") UUID id, @RequestParam("status") String status) {
        log.info("Patch request received for reservation with id: {} to update status to: {}", id, status);
        Reservation reservation = updateStatusReservation.execute(id, status);
        log.info("Reservation status updated successfully with id: {}", reservation.getId());
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all reservations", description = "Retrieve a list of all reservations.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservations retrieved successfully")
    })
    public ResponseEntity<List<Reservation>> getAll() {
        log.info("Get request received for all reservations");
        List<Reservation> reservations = findAllReservations.execute();
        log.info("Retrieved {} reservations", reservations.size());
        return ResponseEntity.ok(reservations);
    }
}
