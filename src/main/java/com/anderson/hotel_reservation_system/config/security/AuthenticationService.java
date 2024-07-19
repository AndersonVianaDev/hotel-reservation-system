package com.anderson.hotel_reservation_system.config.security;

import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.dataprovider.employee.dataprovider.repositories.port.SpringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.EMPLOYEE_NOT_FOUND;

@Component
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private SpringEmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND));
    }
}
