package com.anderson.hotel_reservation_system.config.token.impl;

import com.anderson.hotel_reservation_system.config.token.port.TokenService;
import com.anderson.hotel_reservation_system.core.exceptions.InvalidDataException;
import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.CONFIG_ERROR_MESSAGE;
import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.TOKEN_CREATE_ERROR;
import static java.util.Objects.isNull;

@Component
public class TokenServiceImpl implements TokenService {

    @Value("${jwt-secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    @Override
    public String generator(EmployeeEntity employee) {
        if(isNull(secret) || isNull(expiration) || isNull(employee)) {
            throw new InvalidDataException(CONFIG_ERROR_MESSAGE);
        }

        try {
            return JWT.create()
                    .withIssuer("auth")
                    .withSubject(employee.getId().toString())
                    .withExpiresAt(new Date(new Date().getTime() + Long.parseLong(expiration)))
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e) {
            throw new RuntimeException(TOKEN_CREATE_ERROR);
        }
    }

    @Override
    public UUID getId(String token) {
        try {
            return UUID.fromString(
                    JWT.require(Algorithm.HMAC256(secret))
                            .withIssuer("auth")
                            .build()
                            .verify(token)
                            .getSubject()
            );
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
