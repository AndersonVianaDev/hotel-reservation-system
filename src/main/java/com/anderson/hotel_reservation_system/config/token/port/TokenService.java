package com.anderson.hotel_reservation_system.config.token.port;

import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;

import java.util.UUID;

public interface TokenService {
    String generator(EmployeeEntity employee);
    UUID getId(String token);
}
