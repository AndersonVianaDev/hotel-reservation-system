package com.anderson.hotel_reservation_system.entrypoint.employee;

import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
import com.anderson.hotel_reservation_system.entrypoint.employee.dtos.EmployeeResponseDTO;
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

import static com.anderson.hotel_reservation_system.entrypoint.employee.builders.EmployeeBuilderTest.toEmployeeEntity1;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FindEmployeeByIdTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringEmployeeRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("find employee by id successfully")
    void findById() throws Exception {
        UUID id = repository.save(toEmployeeEntity1()).getId();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employee/get/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        EmployeeResponseDTO responseDTO = mapper.readValue(content, EmployeeResponseDTO.class);

        assertEquals(id, responseDTO.id());
    }

    @Test
    @DisplayName("not found exception")
    void findByIdWithNotFound() throws Exception{
        UUID id = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
