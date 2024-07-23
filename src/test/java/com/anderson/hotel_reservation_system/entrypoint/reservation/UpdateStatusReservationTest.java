package com.anderson.hotel_reservation_system.entrypoint.reservation;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.enums.ReservationStatus;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.impl.CustomerRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
import com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.impl.ReservationRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.impl.RoomRepositoryImpl;
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

import static com.anderson.hotel_reservation_system.entrypoint.customer.builders.CustomerBuilderTest.toCustomer1;
import static com.anderson.hotel_reservation_system.entrypoint.reservation.builders.ReservationBuilderTest.toReservation;
import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.toRoom1;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UpdateStatusReservationTest {


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationRepositoryImpl repository;

    @Autowired
    private RoomRepositoryImpl roomRepository;

    @Autowired
    private CustomerRepositoryImpl customerRepository;

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
    @DisplayName("update status reservation successfully")
    void update() throws Exception {
        Room room = roomRepository.save(toRoom1());
        Customer customer = customerRepository.save(toCustomer1());
        UUID id = repository.save(toReservation(customer, room)).getId();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/reservation/patch/"+id)
                .param("status", "in_use"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Reservation reservation = mapper.readValue(content, new TypeReference<Reservation>() {});

        assertSame(ReservationStatus.IN_USE, reservation.getStatus());
    }
}
