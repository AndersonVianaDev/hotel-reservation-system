package com.anderson.hotel_reservation_system.entrypoint.customer;

import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.port.SpringCustomerRepository;
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

import static com.anderson.hotel_reservation_system.entrypoint.customer.builders.CustomerBuilderTest.toCustomerEntity1;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UpdateCustomerTest {

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
    @DisplayName("successfully updating customer")
    void update() throws Exception {
        UUID id = repository.save(toCustomerEntity1()).getId();
        CustomerDTO dto = new CustomerDTO("updated", "update@gmail.com", "6543213545");

        String dtoString = mapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/customer/put/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        CustomerEntity updatedCustomer = mapper.readValue(content, CustomerEntity.class);

        assertEquals(dto.name(), updatedCustomer.getName());
        assertEquals(dto.email(), updatedCustomer.getEmail());
        assertEquals(dto.phone(), updatedCustomer.getPhone());
    }
}
