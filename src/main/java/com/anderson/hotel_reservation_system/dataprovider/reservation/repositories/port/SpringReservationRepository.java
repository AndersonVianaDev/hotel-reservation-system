package com.anderson.hotel_reservation_system.dataprovider.reservation.repositories.port;

import com.anderson.hotel_reservation_system.dataprovider.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringReservationRepository extends JpaRepository<ReservationEntity, UUID> {
    @Query("SELECT res FROM ReservationEntity res WHERE res.checkIn >= :startDate AND res.checkOut <= :endDate")
    List<ReservationEntity> findAllByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT res " +
            "FROM ReservationEntity res " +
            "JOIN res.room r " +
            "WHERE r.id = :id " +
            "AND (:startDate <= res.checkOut AND :endDate >= res.checkIn)")
    Optional<ReservationEntity> findReservationByRoomIdAndReservationDate(@Param("id") UUID id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
