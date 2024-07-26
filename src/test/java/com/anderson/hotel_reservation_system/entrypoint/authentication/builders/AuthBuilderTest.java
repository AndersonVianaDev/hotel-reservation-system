package com.anderson.hotel_reservation_system.entrypoint.authentication.builders;

import com.anderson.hotel_reservation_system.entrypoint.authentication.dtos.LoginDTO;

public class AuthBuilderTest {
    public static LoginDTO toLoginDTO() {
        return new LoginDTO("test1@gmail.com", "test1234");
    }

    public static LoginDTO toLoginDTOWithWrongPassword() {
        return new LoginDTO("test1@gmail.com", "test");
    }
}
