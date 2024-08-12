package com.anderson.hotel_reservation_system.entrypoint.room;

import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;
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

import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.toRoomEntity1;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class DeleteRoomTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringRoomRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("successfully deleting room")
    void delete() throws Exception {
        UUID id = repository.save(toRoomEntity1()).getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/room/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        Optional<RoomEntity> room = repository.findById(id);

        assertFalse(room.isPresent(), "Room should be deleted");
    }

}
