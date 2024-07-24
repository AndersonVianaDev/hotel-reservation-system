package com.anderson.hotel_reservation_system.entrypoint.reservation;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.impl.CustomerRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.customer.repositories.port.SpringCustomerRepository;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
import com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.impl.ReservationRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.port.SpringReservationRepository;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.impl.RoomRepositoryImpl;
import com.anderson.hotel_reservation_system.dataprovider.room.repositories.port.SpringRoomRepository;
import com.anderson.hotel_reservation_system.entrypoint.reservation.dtos.ReservationRequestDTO;
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

import java.time.LocalDate;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.customer.builders.CustomerBuilderTest.toCustomer1;
import static com.anderson.hotel_reservation_system.entrypoint.reservation.builders.ReservationBuilderTest.toReservationRequestDTO;
import static com.anderson.hotel_reservation_system.entrypoint.reservation.builders.ReservationBuilderTest.toReservationRequestDTOWithInvalidData;
import static com.anderson.hotel_reservation_system.entrypoint.room.builders.RoomBuilderTest.toRoom1;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RegisterReservationTest {

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
    @DisplayName("Register reservation successfully")
    void register() throws Exception {
        Room room = roomRepository.save(toRoom1());
        UUID idRoom = room.getId();
        Customer customer = customerRepository.save(toCustomer1());
        UUID idCustomer = customer.getId();
        ReservationRequestDTO dto = toReservationRequestDTO();

        String dtoString = mapper.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/reservation/register/" + idCustomer + "/" + idRoom)
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Reservation reservation = mapper.readValue(content, new TypeReference<Reservation>() {});

        assertEquals(room, reservation.getRoom());
        assertEquals(customer, reservation.getCustomer());
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
}
