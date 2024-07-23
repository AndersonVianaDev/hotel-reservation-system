package com.anderson.hotel_reservation_system.entrypoint.room;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.impl.CustomerRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.impl.ReservationRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.impl.RoomRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.port.SpringRoomRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static com.anderson.hotel_reservation_system.entrypoint.customer.builders.CustomerBuilderTest.toCustomer2;
import static com.anderson.hotel_reservation_system.entrypoint.reservation.builders.ReservationBuilderTest.toReservation;
import static com.anderson.hotel_reservation_system.entrypoint.reservation.builders.ReservationBuilderTest.toReservationIN_USE;
import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.toRoom1;
import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.toRoom2;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FindAllOccupiedRoomsTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoomRepositoryImpl repository;

    @Autowired
    private ReservationRepositoryImpl reservationRepository;

    @Autowired
    private CustomerRepositoryImpl customerRepository;

    @Test
    @DisplayName("find all occupied rooms successfully")
    void findAllOccupied() throws Exception {
        Room room1 = repository.save(toRoom1());
        Room room2 = repository.save(toRoom2());
        Customer customer1 = customerRepository.save(toCustomer1());
        Customer customer2 = customerRepository.save(toCustomer2());
        reservationRepository.save(toReservationIN_USE(customer1, room1));
        reservationRepository.save(toReservation(customer2, room2));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/room/getOccupied"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        List<Room> rooms = mapper.readValue(content, new TypeReference<List<Room>>() {});

        assertEquals(1, rooms.size());
    }
}
