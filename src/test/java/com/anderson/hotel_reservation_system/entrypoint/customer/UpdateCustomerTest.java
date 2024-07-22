package com.anderson.hotel_reservation_system.entrypoint.customer;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.impl.CustomerRepositoryImpl;
import com.anderson.hotel_reservation_system.entrypoint.customer.builders.CustomerBuilderTest;
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

import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.customer.builders.CustomerBuilderTest.toCustomer1;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UpdateCustomerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public CustomerRepositoryImpl repository;

    @Test
    @DisplayName("successfully updating customer")
    void update() throws Exception {
        Customer savedCustomer = repository.save(toCustomer1());
        UUID id = savedCustomer.getId();
        CustomerDTO dto = new CustomerDTO("updated", "update@gmail.com", "6543213545");

        String dtoString = mapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/customer/put/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Customer updatedCustomer = mapper.readValue(content, new TypeReference<Customer>() {});

        assertEquals(dto.name(), updatedCustomer.getName());
        assertEquals(dto.email(), updatedCustomer.getEmail());
        assertEquals(dto.phone(), updatedCustomer.getPhone());
    }
}
