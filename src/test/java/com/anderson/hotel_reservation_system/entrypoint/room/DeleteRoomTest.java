package com.anderson.hotel_reservation_system.entrypoint.room;

import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.impl.RoomRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.port.SpringRoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.toRoom1;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class DeleteRoomTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoomRepositoryImpl repository;

    @Autowired
    private SpringRoomRepository springRepository;

    @BeforeEach
    void setup() {
        springRepository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        springRepository.deleteAll();
    }

    @Test
    @DisplayName("successfully deleting room")
    void delete() throws Exception {
        UUID id = repository.save(toRoom1()).getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/room/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        Optional<Room> room = repository.findById(id);

        assertFalse(room.isPresent(), "Room should be deleted");
    }

}
