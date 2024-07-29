package com.anderson.hotel_reservation_system.config.security;

import com.anderson.hotel_reservation_system.core.exceptions.StandardException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.Instant;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.FORBIDDEN;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private ObjectMapper mapper;

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/employee/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/employee/getAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/employee/delete").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(handling -> handling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            StandardException exception = new StandardException(Instant.now(), HttpStatus.FORBIDDEN.value(), FORBIDDEN, request.getRequestURI());
                            response.setStatus(exception.getStatus());
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(mapper.writeValueAsString(exception));
                        }))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
