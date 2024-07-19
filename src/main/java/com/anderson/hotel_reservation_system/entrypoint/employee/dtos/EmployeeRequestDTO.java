package com.anderson.hotel_reservation_system.entrypoint.employee.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeRequestDTO(@NotNull(message = "name field cannot be null.") String name,
                                 @Email(message = "email format is not valid.") @NotNull(message = "email field cannot be null.") String email,
                                 @NotNull(message = "password field cannot be null.") @Size(min = 8, max = 16, message = "the password must be between 8 to 16 digits.") String password,
                                 @NotNull(message = "type field cannot be null.") String type) {
}
