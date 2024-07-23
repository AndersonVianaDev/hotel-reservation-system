package com.anderson.hotel_reservation_system.entrypoint.employee;

import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.impl.EmployeeRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
import com.anderson.hotel_reservation_system.entrypoint.employee.dtos.EmployeeResponseDTO;
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

import java.util.List;

import static com.anderson.hotel_reservation_system.entrypoint.employee.builders.EmployeeBuilderTest.toEmployeesEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FindAllEmployeesTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepositoryImpl repository;

    @Autowired
    private SpringEmployeeRepository springRepository;

    @BeforeEach
    void setup() {
        springRepository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        springRepository.deleteAll();
    }

    @Test
    @DisplayName("find all employees successfully")
    void findAll() throws Exception {
        springRepository.saveAll(toEmployeesEntity());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employee/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        List<EmployeeResponseDTO> responseDTOS = mapper.readValue(content, new TypeReference<List<EmployeeResponseDTO>>() {});

        assertEquals(toEmployeesEntity().size(), responseDTOS.size());
    }
}
