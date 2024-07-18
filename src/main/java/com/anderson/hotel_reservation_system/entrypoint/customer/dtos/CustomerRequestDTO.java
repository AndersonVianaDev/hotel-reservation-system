package com.anderson.hotel_reservation_system.entrypoint.customer.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerRequestDTO(@NotNull(message = "name field cannot be null.") String name,
                                 @Email(message = "email format is not valid.") @NotNull(message = "email field cannot be null.") String email,
                                 @NotNull(message = "phone field cannot be null.") @Size(min = 9, max = 11, message = "the phone must be between 9 to 11 digits.") String phone) {
}
