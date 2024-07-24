package com.anderson.hotel_reservation_system.entrypoint.reservation;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.impl.CustomerRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.port.SpringCustomerRepository;
import com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.impl.ReservationRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.port.SpringReservationRepository;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.impl.RoomRepositoryImpl;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.anderson.hotel_reservation_system.entrypoint.customer.builders.CustomerBuilderTest.toCustomer1;
import static com.anderson.hotel_reservation_system.entrypoint.reservation.builders.ReservationBuilderTest.toReservation;
import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.toRoom1;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FindAllReservationsByDateRangeTest {

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
    private SpringReservationRepository springRepository;

    @Autowired
    private SpringCustomerRepository springCustomerRepository;

    @Autowired
    private SpringRoomRepository springRoomRepository;

    @BeforeEach
    void setup() {
        springRepository.deleteAll();
        springCustomerRepository.deleteAll();
        springRoomRepository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        springRepository.deleteAll();
        springCustomerRepository.deleteAll();
        springRoomRepository.deleteAll();
    }

    @Test
    @DisplayName("find all reservations by date range successfully")
    void findAll() throws Exception {
        Room room = roomRepository.save(toRoom1());
        Customer customer = customerRepository.save(toCustomer1());
        repository.save(toReservation(customer, room));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/reservation/get")
                        .param("startDate", "2024-07-20")
                        .param("endDate", "2024-07-30"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        List<Reservation> reservations = mapper.readValue(content, new TypeReference<List<Reservation>>() {});

        assertEquals(1, reservations.size());
    }
}
