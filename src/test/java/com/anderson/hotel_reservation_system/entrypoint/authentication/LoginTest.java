package com.anderson.hotel_reservation_system.entrypoint.authentication;

import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.RegisterEmployeeUseCasePort;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
import com.anderson.hotel_reservation_system.entrypoint.authentication.dtos.LoginDTO;
import com.anderson.hotel_reservation_system.entrypoint.authentication.dtos.TokenResponseDTO;
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

import static com.anderson.hotel_reservation_system.entrypoint.authentication.builders.AuthBuilderTest.toLoginDTO;
import static com.anderson.hotel_reservation_system.entrypoint.authentication.builders.AuthBuilderTest.toLoginDTOWithWrongPassword;
import static com.anderson.hotel_reservation_system.entrypoint.employee.builders.EmployeeBuilderTest.toEmployeeDTO;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class LoginTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RegisterEmployeeUseCasePort registerEmployee;

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
    @DisplayName("Login successfully")
    void login() throws Exception{
        EmployeeDTO employeeDTO = toEmployeeDTO();
        registerEmployee.execute(employeeDTO);

        LoginDTO loginDTO = toLoginDTO();
        String dtoString = mapper.writeValueAsString(loginDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        TokenResponseDTO tokenResponseDTO = mapper.readValue(content, new TypeReference<TokenResponseDTO>() {});

        assertNotNull(tokenResponseDTO);
    }

    @Test
    @DisplayName("Wrong Password")
    void loginWithWrongPassword() throws Exception {
        EmployeeDTO employeeDTO = toEmployeeDTO();
        registerEmployee.execute(employeeDTO);

        LoginDTO dto = toLoginDTOWithWrongPassword();

        String dtoString = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
    }
}
