package com.anderson.hotel_reservation_system.entrypoint.room.controller;

import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.core.room.usecases.ports.*;
import com.anderson.hotel_reservation_system.entrypoint.room.dtos.RoomRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.room.mapper.RoomRequestDTOMapper.toRoomDTO;

@RestController
@RequestMapping(value = "/room")
public class RoomController {

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

    @PostMapping("/register")
    public ResponseEntity<Room> register(@Valid @RequestBody RoomRequestDTO dto) {
        RoomDTO roomDTO = toRoomDTO(dto);
        Room room = registerRoom.execute(roomDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(room)
                .toUri())
                .body(room);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Room> get(@PathVariable("id") UUID id) {
        Room room = findRoomById.execute(id);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/getOccupied")
    public ResponseEntity<List<Room>> getOccupiedRooms() {
        List<Room> rooms = findAllOccupiedRooms.execute();
        return ResponseEntity.ok(rooms);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Room> put(@PathVariable("id") UUID id, @Valid @RequestBody RoomRequestDTO dto) {
        RoomDTO roomDTO = toRoomDTO(dto);
        Room room = updateRoom.execute(id, roomDTO);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteRoom.execute(id);
        return ResponseEntity.noContent().build();
    }
}
