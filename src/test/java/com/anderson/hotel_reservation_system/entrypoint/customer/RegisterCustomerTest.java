package com.anderson.hotel_reservation_system.entrypoint.customer;

import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.port.SpringCustomerRepository;
import com.anderson.hotel_reservation_system.entrypoint.customer.dtos.CustomerRequestDTO;
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

import static com.anderson.hotel_reservation_system.entrypoint.customer.builders.CustomerBuilderTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RegisterCustomerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public SpringCustomerRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Register customer successfully")
    void registerSuccessfully() throws Exception {
        CustomerDTO dto = toCustomerDTO();

        String dtoString = mapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        CustomerEntity customer = mapper.readValue(content, CustomerEntity.class);

        assertEquals(dto.name(), customer.getName());
        assertEquals(dto.email(), customer.getEmail());
        assertEquals(dto.phone(), customer.getPhone());
    }

    @Test
    @DisplayName("data conflict exception")
    void registerWithDataConflict() throws Exception {
        CustomerEntity customer = toCustomerEntity1();
        repository.save(customer);

        CustomerDTO dto = toCustomerDTO();
        String dtoString = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("method argument not valid exception")
    void registerWithMethodArgumentNotValidException() throws Exception {
        CustomerRequestDTO dto = toCustomerRequestDTO();
        String dtoString = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}
