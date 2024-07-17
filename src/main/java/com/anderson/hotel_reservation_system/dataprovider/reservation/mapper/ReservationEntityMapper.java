package com.anderson.hotel_reservation_system.dataprovider.reservation.mapper;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.reservation.builder.ReservationBuilder;
import com.anderson.hotel_reservation_system.core.reservation.domain.Reservation;
import com.anderson.hotel_reservation_system.core.room.domain.Room;
import com.anderson.hotel_reservation_system.dataprovider.customer.entity.CustomerEntity;
import com.anderson.hotel_reservation_system.dataprovider.reservation.builder.ReservationEntityBuilder;
import com.anderson.hotel_reservation_system.dataprovider.reservation.entity.ReservationEntity;
import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;

import java.util.List;
import java.util.Optional;

import static com.anderson.hotel_reservation_system.dataprovider.customer.mapper.CustomerEntityMapper.toCustomer;
import static com.anderson.hotel_reservation_system.dataprovider.room.mapper.RoomEntityMapper.toRoom;

public class ReservationEntityMapper {
    public static ReservationEntity toReservationEntity(Reservation reservation, CustomerEntity customer, RoomEntity room) {
        return new ReservationEntityBuilder()
                .withCheckIn(reservation.getCheckIn())
                .withCheckOut(reservation.getCheckOut())
                .withStatus(reservation.getStatus())
                .withCustomer(customer)
                .withRoom(room)
                .build();
    }

    public static Reservation toReservation(ReservationEntity reservationEntity) {
        Customer customer = toCustomer(reservationEntity.getCustomer());
        Room room = toRoom(reservationEntity.getRoom());
        return new ReservationBuilder()
                .withId(reservationEntity.getId())
                .withCheckIn(reservationEntity.getCheckIn())
                .withCheckOut(reservationEntity.getCheckOut())
                .withStatus(reservationEntity.getStatus())
                .withCustomer(customer)
                .withRoom(room)
                .build();
    }

    public static Optional<Reservation> toOptionalReservation(Optional<ReservationEntity> reservationEntity) {
        return reservationEntity.map(ReservationEntityMapper::toReservation);
    }

    public static List<Reservation> toReservationList(List<ReservationEntity> reservationEntities) {
        return reservationEntities.stream().map(ReservationEntityMapper::toReservation).toList();
    }
}
