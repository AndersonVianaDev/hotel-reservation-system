package com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.port;

import com.anderson.hotel_reservation_system.dataprovider.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface SpringReservationRepository extends JpaRepository<ReservationEntity, UUID> {
    @Query("SELECT res FROM ReservationEntity res WHERE res.checkIn >= :checkIn AND res.checkOut <= :checkOut")
    List<ReservationEntity> findAllByDateRange(@Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut);
}
