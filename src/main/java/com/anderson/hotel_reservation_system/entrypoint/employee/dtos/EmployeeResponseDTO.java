package com.anderson.hotel_reservation_system.entrypoint.employee.dtos;

import java.util.UUID;

public record EmployeeResponseDTO(UUID id, String name, String email) {
}
