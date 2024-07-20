package com.anderson.hotel_reservation_system.entrypoint.authentication.controller;

import com.anderson.hotel_reservation_system.config.token.port.TokenService;
import com.anderson.hotel_reservation_system.dataprovider.employee.entity.EmployeeEntity;
import com.anderson.hotel_reservation_system.entrypoint.authentication.dtos.LoginDTO;
import com.anderson.hotel_reservation_system.entrypoint.authentication.dtos.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generator((EmployeeEntity) auth.getPrincipal());
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
