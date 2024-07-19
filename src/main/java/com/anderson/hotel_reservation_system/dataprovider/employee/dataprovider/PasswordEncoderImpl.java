package com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider;

import com.anderson.hotel_reservation_system.core.employee.dataprovider.PasswordEncoderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoderPort {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
