package com.anderson.hotel_reservation_system.config.security;

import com.anderson.hotel_reservation_system.config.token.impl.TokenServiceImpl;
import com.anderson.hotel_reservation_system.config.token.port.TokenService;
import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SpringEmployeeRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if(nonNull(token)) authenticateEmployee(token);
        filterChain.doFilter(request, response);
    }

    private void authenticateEmployee(String token) {
        UUID id = tokenService.getId(token);
        EmployeeEntity employeeEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND));
        var authentication = new UsernamePasswordAuthenticationToken(employeeEntity, null, employeeEntity.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(isNull(token) || !token.startsWith("Bearer ")) return null;
        return token.replace("Bearer ", "");
    }
}
