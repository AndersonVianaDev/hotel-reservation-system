package com.anderson.hotel_reservation_system.entrypoint.reservation;

import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.port.SpringCustomerRepository;
import com.anderson.hotel_reservation_system.dataprovider.reservation.entity.ReservationEntity;
import com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.port.SpringReservationRepository;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.port.SpringRoomRepository;
import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.ReservationRequestDTO;
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
import static com.anderson.hotel_reservation_system.entrypoint.reservation.builders.ReservationBuilderTest.*;
import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.toRoomEntity1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RegisterReservationTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringReservationRepository reservationRepository;

    @Autowired
    private SpringCustomerRepository customerRepository;

    @Autowired
    private SpringRoomRepository roomRepository;

    @BeforeEach
    void setup() {
        reservationRepository.deleteAll();
        customerRepository.deleteAll();
        roomRepository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        reservationRepository.deleteAll();
        customerRepository.deleteAll();
        roomRepository.deleteAll();
    }

    @Test
    @DisplayName("Register reservation successfully")
    void register() throws Exception {
        UUID idRoom = roomRepository.save(toRoomEntity1()).getId();
        UUID idCustomer = customerRepository.save(toCustomerEntity1()).getId();

        ReservationRequestDTO dto = toReservationRequestDTO();

        String dtoString = mapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/reservation/register/" + idCustomer + "/" + idRoom)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ReservationEntity reservation = mapper.readValue(content, ReservationEntity.class);

        assertNotNull(reservation);
    }

    @Test
    @DisplayName("invalid data exception")
    void registerWithInvalidData() throws Exception {
        UUID idCustomer = UUID.randomUUID();
        UUID idRoom = UUID.randomUUID();
        ReservationRequestDTO dto = toReservationRequestDTOWithInvalidData();
        String dtoString = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservation/register/"+idCustomer+"/"+idRoom)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("method argument not valid exception")
    void registerWithMethodArgumentNotValidException() throws Exception {
        ReservationRequestDTO dto = toReservationRequestDTOWithMethodArgumentNotValid();
        UUID idCustomer = UUID.randomUUID();
        UUID idRoom = UUID.randomUUID();

        String dtoString = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservation/register/"+idCustomer+"/"+idRoom)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}
