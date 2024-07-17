package com.anderson.hotel_reservation_system.dataprovider.reservation.builder;

import com.anderson.hotel_reservation_system.core.reservation.enums.ReservationStatus;
import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;
import com.anderson.hotel_reservation_system.dataprovider.reservation.entity.ReservationEntity;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;

import java.time.LocalDate;
import java.util.UUID;

public class ReservationEntityBuilder {
    private UUID id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private ReservationStatus status;
    private CustomerEntity customer;
    private RoomEntity room;

    public ReservationEntityBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public ReservationEntityBuilder withCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public ReservationEntityBuilder withCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public ReservationEntityBuilder withStatus(ReservationStatus status) {
        this.status = status;
        return this;
    }

    public ReservationEntityBuilder withCustomer(CustomerEntity customer) {
        this.customer = customer;
        return this;
    }

    public ReservationEntityBuilder withRoom(RoomEntity room) {
        this.room = room;
        return this;
    }

    public ReservationEntity build() {
        return new ReservationEntity(id, checkIn, checkOut, status, customer, room);
    }

}
