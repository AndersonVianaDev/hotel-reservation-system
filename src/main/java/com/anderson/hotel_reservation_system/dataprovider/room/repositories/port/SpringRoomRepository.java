package com.anderson.hotel_reservation_system.dataprovider.room.repositories.port;

import com.anderson.hotel_reservation_system.dataprovider.room.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringRoomRepository extends JpaRepository<RoomEntity, UUID> {
    @Query("SELECT r FROM RoomEntity r WHERE r.roomNumber = :roomNumber")
    Optional<RoomEntity> findByRoomNumber(@Param("roomNumber") String roomNumber);

    @Query("SELECT r FROM RoomEntity r JOIN ReservationEntity res ON res.room.id = r.id WHERE res.status = 'IN_USE'")
    List<RoomEntity> findAllOccupiedRooms();
}
