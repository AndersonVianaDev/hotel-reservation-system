package com.anderson.hotel_reservation_system.entrypoint.room.controller;

import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.*;
import com.anderson.hotel_reservation_system.entrypoint.room.dtos.RoomRequestDTO;
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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.room.mapper.RoomRequestDTOMapper.toRoomDTO;

@RestController
@RequestMapping(value = "/room")
@Tag(name = "Room Controller", description = "Operations related to rooms in the hotel reservation system.")
public class RoomController {

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RegisterRoomUseCasePort registerRoom;

    @Autowired
    private FindRoomByIdUseCasePort findRoomById;

    @Autowired
    private FindAllOccupiedRoomsUseCasePort findAllOccupiedRooms;

    @Autowired
    private UpdateRoomUseCasePort updateRoom;

    @Autowired
    private DeleteRoomUseCasePort deleteRoom;

    @Autowired
    private FindAllRoomsUseCasePort findAllRooms;

    @Autowired
    private FindAllAvailableRoomsUseCasePort findAllAvailableRooms;

    @PostMapping("/register")
    @Operation(summary = "Register a new room", description = "Register a new room with the provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Room created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Room> register(@Valid @RequestBody RoomRequestDTO dto) {
        log.info("Register request received for room");
        RoomDTO roomDTO = toRoomDTO(dto);
        Room room = registerRoom.execute(roomDTO);
        log.info("Room with id {} created successfully", room.getId());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(room)
                .toUri())
                .body(room);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get room by ID", description = "Retrieve a room by its unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Room found and returned"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    public ResponseEntity<Room> get(@PathVariable("id") UUID id) {
        log.info("Get request received for room with id: {}", id);
        Room room = findRoomById.execute(id);
        log.info("Room retrieved successfully with id: {}", room.getId());
        return ResponseEntity.ok(room);
    }

    @GetMapping("/getOccupied")
    @Operation(summary = "Get all occupied rooms", description = "Retrieve a list of all occupied rooms.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Occupied rooms retrieved successfully")
    })
    public ResponseEntity<List<Room>> getOccupiedRooms() {
        log.info("Get request received for all occupied rooms");
        List<Room> rooms = findAllOccupiedRooms.execute();
        log.info("Retrieved {} occupied rooms", rooms.size());
        return ResponseEntity.ok(rooms);
    }

    @PutMapping("/put/{id}")
    @Operation(summary = "Update room by ID", description = "Update the details of an existing room using its unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Room updated successfully"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Room> put(@PathVariable("id") UUID id, @Valid @RequestBody RoomRequestDTO dto) {
        log.info("Update request received for room with id: {} with details: {}", id, dto);
        RoomDTO roomDTO = toRoomDTO(dto);
        Room room = updateRoom.execute(id, roomDTO);
        log.info("Room with id {} updated successfully", room.getId());
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete room by ID", description = "Delete an existing room using its unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Room deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        log.info("Delete request received for room with id: {}", id);
        deleteRoom.execute(id);
        log.info("Room with id {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all rooms", description = "Retrieve a list of all rooms.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rooms retrieved successfully")
    })
    public ResponseEntity<List<Room>> getAll() {
        log.info("Get request received for all rooms");
        List<Room> rooms = findAllRooms.execute();
        log.info("Retrieved {} rooms", rooms.size());
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/getAvailable")
    @Operation(summary = "Get all available rooms", description = "Retrieve a list of all available rooms.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Available rooms retrieved successfully")
    })
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        log.info("Get request received for all available rooms");
        List<Room> rooms = findAllAvailableRooms.execute(startDate, endDate);
        log.info("Retrieved {} available rooms", rooms.size());
        return ResponseEntity.ok(rooms);
    }
}
