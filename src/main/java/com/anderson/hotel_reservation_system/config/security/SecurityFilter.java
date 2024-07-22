package com.anderson.hotel_reservation_system.config.security;

import com.anderson.hotel_reservation_system.config.token.port.TokenService;
import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.EMPLOYEE_NOT_FOUND;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SpringEmployeeRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Starting security filter");
        String token = recoverToken(request);
        if(nonNull(token)) {
            log.debug("Token found: {}", token);
            authenticateEmployee(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateEmployee(String token) {
        UUID id = tokenService.getId(token);
        log.debug("Authenticating employee with ID: {}", id);
        EmployeeEntity employeeEntity = repository.findById(id).orElseThrow(() -> {
            log.warn("Employee not found with ID: {}", id);
            return new NotFoundException(EMPLOYEE_NOT_FOUND);
        });
        var authentication = new UsernamePasswordAuthenticationToken(employeeEntity, null, employeeEntity.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("Employee authenticated: {}", employeeEntity);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(isNull(token) || !token.startsWith("Bearer ")) return null;
        return token.replace("Bearer ", "");
    }
}
