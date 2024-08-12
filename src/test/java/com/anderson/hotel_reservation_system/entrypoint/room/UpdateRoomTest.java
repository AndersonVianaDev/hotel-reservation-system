package com.anderson.hotel_reservation_system.entrypoint.room;

import com.anderson.hotel_reservation_system.core.room.dtos.RoomDTO;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UpdateRoomTest {

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
    @DisplayName("updating the room successfully")
    void update() throws Exception {
        UUID id = repository.save(toRoomEntity2()).getId();
        RoomDTO dto = toRoomDTO();

        String dtoString = mapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/room/put/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        RoomEntity room = mapper.readValue(content, RoomEntity.class);

        assertEquals(dto.roomNumber(), room.getRoomNumber());
        assertEquals(dto.type(), room.getType());
        assertEquals(dto.price(), room.getPrice());
    }
}
