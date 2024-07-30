package com.anderson.hotel_reservation_system.config.security;

import com.anderson.hotel_reservation_system.core.exceptions.StandardException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.FORBIDDEN;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        StandardException exception = new StandardException(Instant.now(), HttpStatus.FORBIDDEN.value(), FORBIDDEN, request.getRequestURI());
        response.setStatus(exception.getStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(exception));
    }
}
