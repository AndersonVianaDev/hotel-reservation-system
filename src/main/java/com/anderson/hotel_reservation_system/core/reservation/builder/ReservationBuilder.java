package com.anderson.hotel_reservation_system.core.reservation.builder;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.reservation.enums.ReservationStatus;
import com.anderson.hotel_reservation_system.core.room.domain.Room;

import java.time.LocalDate;
import java.util.UUID;

public class ReservationBuilder {
    private UUID id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private ReservationStatus status;
    private Customer customer;
    private Room room;

    public ReservationBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public ReservationBuilder withCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public ReservationBuilder withCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public ReservationBuilder withStatus(ReservationStatus status) {
        this.status = status;
        return this;
    }

    public ReservationBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public ReservationBuilder withRoom(Room room) {
        this.room = room;
        return this;
    }

    public Reservation build() {
        return new Reservation(id, checkIn, checkOut, status, customer, room);
    }
}
