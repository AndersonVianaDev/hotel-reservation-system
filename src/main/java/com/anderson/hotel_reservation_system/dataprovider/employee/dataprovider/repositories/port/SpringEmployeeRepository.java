package com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port;

import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringEmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    Optional<EmployeeEntity> findByEmail(String email);
}
