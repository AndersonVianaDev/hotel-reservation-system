package com.anderson.hotel_reservation_system.core.employee.usecases.ports;

import java.util.UUID;

public interface DeleteEmployeeUseCasePort {
    void execute(UUID id);
}
