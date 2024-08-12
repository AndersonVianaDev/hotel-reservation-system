package com.anderson.hotel_reservation_system.entrypoint.room;

import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.port.SpringCustomerRepository;
import com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.port.SpringReservationRepository;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;
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

import java.time.LocalDate;
import java.util.List;

import static com.anderson.hotel_reservation_system.entrypoint.customer.builders.CustomerBuilderTest.*;
import static com.anderson.hotel_reservation_system.entrypoint.reservation.builders.ReservationBuilderTest.toReservationEntityTest;
import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FindAllAvailableRoomsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringRoomRepository roomRepository;

    @Autowired
    private SpringReservationRepository reservationRepository;

    @Autowired
    private SpringCustomerRepository customerRepository;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        reservationRepository.deleteAll();
        roomRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        reservationRepository.deleteAll();
        roomRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    @DisplayName("find all available rooms successfully")
    void findAllAvailable() throws Exception {
        RoomEntity room1 = roomRepository.save(toRoomEntity1());
        RoomEntity room2 = roomRepository.save(toRoomEntity2());
        RoomEntity room3 = roomRepository.save(toRoomEntity3());
        CustomerEntity customer1 = customerRepository.save(toCustomerEntity1());
        CustomerEntity customer2 = customerRepository.save(toCustomerEntity2());
        LocalDate startDate = reservationRepository.save(toReservationEntityTest(customer1, room1)).getCheckIn();
        LocalDate endDate = reservationRepository.save(toReservationEntityTest(customer2, room2)).getCheckOut();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/room/getAvailable")
                .param("startDate", String.valueOf(startDate))
                .param("endDate", String.valueOf(endDate)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        List<RoomEntity> rooms = mapper.readValue(content, new TypeReference<List<RoomEntity>>() {});

        assertEquals(1, rooms.size());
        assertEquals(room3.getId(), rooms.get(0).getId());
    }
}
