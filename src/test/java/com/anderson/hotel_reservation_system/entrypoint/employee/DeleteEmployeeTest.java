package com.anderson.hotel_reservation_system.entrypoint.employee;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.impl.EmployeeRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
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

import static com.anderson.hotel_reservation_system.entrypoint.employee.builders.EmployeeBuilderTest.toEmployee1;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class DeleteEmployeeTest {

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
    @DisplayName("successfully deleting employee")
    void delete() throws Exception{
        UUID id = repository.save(toEmployee1()).getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        Optional<Employee> employee = repository.findById(id);

        assertFalse(employee.isPresent(), "Employee should be deleted");
    }
}
