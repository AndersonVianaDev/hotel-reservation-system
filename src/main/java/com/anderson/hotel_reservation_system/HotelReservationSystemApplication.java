package com.anderson.hotel_reservation_system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "hotel-reservation-system",
		version = "1",
		description = "Hotel reservation management system. This service allows for the administration of customers, rooms, and reservations, offering functionalities such as registering new customers and employees, managing room availability, and tracking reservation statuses."),
		security = @SecurityRequirement(name = "Authorization"),
		servers = { @Server(url = "/", description = "Default Server URL") }
)
@SecurityScheme(
		name = "Authorization",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT"
)
public class HotelReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelReservationSystemApplication.class, args);
	}

}
