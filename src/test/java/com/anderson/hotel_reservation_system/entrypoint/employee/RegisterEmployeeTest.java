package com.anderson.hotel_reservation_system.entrypoint.employee;

import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.impl.EmployeeRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
import com.anderson.hotel_reservation_system.entrypoint.employee.dtos.EmployeeResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static com.anderson.hotel_reservation_system.entrypoint.employee.builders.EmployeeBuilderTest.toEmployee1;
import static com.anderson.hotel_reservation_system.entrypoint.employee.builders.EmployeeBuilderTest.toEmployeeDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RegisterEmployeeTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepositoryImpl repository;

    @Autowired
    private SpringEmployeeRepository springRepository;

    @Test
    @DisplayName("Register employee successfully")
    void register() throws Exception {
        EmployeeDTO dto = toEmployeeDTO();
        String dtoString = mapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/employee/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        EmployeeResponseDTO employee = mapper.readValue(content, new TypeReference<EmployeeResponseDTO>() {});

        assertEquals(dto.name(), employee.name());
        assertEquals(dto.email(), employee.email());
    }

    @Test
    @DisplayName("data conflict exception")
    void registerWithDataConflict() throws Exception {
        repository.save(toEmployee1());

        EmployeeDTO dto = toEmployeeDTO();
        String dtoString = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
}
