package com.anderson.hotel_reservation_system.entrypoint.customer.controller;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.DeleteCustomerUseCasePort;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.FindCustomerByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.RegisterCustomerUseCasePort;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.UpdateCustomerUseCasePort;
import com.anderson.hotel_reservation_system.entrypoint.customer.dtos.CustomerRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.customer.mapper.CustomerRequestDTOMapper.toCustomerDTO;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private RegisterCustomerUseCasePort registerCustomer;

    @Autowired
    private FindCustomerByIdUseCasePort findCustomerById;

    @Autowired
    private UpdateCustomerUseCasePort updateCustomer;

    @Autowired
    private DeleteCustomerUseCasePort deleteCustomer;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@Valid @RequestBody CustomerRequestDTO dto) {
        CustomerDTO customerDTO = toCustomerDTO(dto);
        Customer customer = registerCustomer.execute(customerDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri())
                .body(customer);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Customer> get(@PathVariable("id") UUID id) {
        Customer customer = findCustomerById.execute(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Customer> put(@PathVariable("id") UUID id, @Valid @RequestBody CustomerRequestDTO dto) {
        CustomerDTO customerDTO = toCustomerDTO(dto);
        Customer customer = updateCustomer.execute(id, customerDTO);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteCustomer.execute(id);
        return ResponseEntity.noContent().build();
    }
}
