package com.anderson.hotel_reservation_system.entrypoint.room;

import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.impl.RoomRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.port.SpringRoomRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.toRoom1;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FindRoomByIdTest {

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
    @DisplayName("find room by id successfully")
    void findById() throws Exception {
        Room savedRoom = repository.save(toRoom1());
        UUID id = savedRoom.getId();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/room/get/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Room room = mapper.readValue(content, new TypeReference<Room>() {});

        assertEquals(savedRoom, room);
    }

    @Test
    @DisplayName("not found exception")
    void findByIdWithNotFound() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.get("/room/get/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
