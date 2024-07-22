package com.anderson.hotel_reservation_system.entrypoint.customer.controller;

import com.anderson.hotel_reservation_system.core.customer.domain.Customer;
import com.anderson.hotel_reservation_system.core.customer.dtos.CustomerDTO;
import com.anderson.hotel_reservation_system.core.customer.usecases.ports.*;
import com.anderson.hotel_reservation_system.entrypoint.customer.dtos.CustomerRequestDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.customer.mapper.CustomerRequestDTOMapper.toCustomerDTO;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private RegisterCustomerUseCasePort registerCustomer;

    @Autowired
    private FindCustomerByIdUseCasePort findCustomerById;

    @Autowired
    private UpdateCustomerUseCasePort updateCustomer;

    @Autowired
    private DeleteCustomerUseCasePort deleteCustomer;

    @Autowired
    private FindAllCustomersUseCasePort findAllCustomers;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@Valid @RequestBody CustomerRequestDTO dto) {
        log.info("Register request received for customer");
        CustomerDTO customerDTO = toCustomerDTO(dto);
        Customer customer = registerCustomer.execute(customerDTO);
        log.info("Customer with id {} created successfully", customer.getId());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri())
                .body(customer);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Customer> get(@PathVariable("id") UUID id) {
        log.info("Get request received for customer with id: {}", id);
        Customer customer = findCustomerById.execute(id);
        log.info("Customer retrieved successfully with id: {}", customer.getId());
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Customer> put(@PathVariable("id") UUID id, @Valid @RequestBody CustomerRequestDTO dto) {
        log.info("Put request received for customer with id: {}", id);
        CustomerDTO customerDTO = toCustomerDTO(dto);
        Customer customer = updateCustomer.execute(id, customerDTO);
        log.info("Customer with id {} updated successfully", customer.getId());
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        log.info("Delete request received for customer with id: {}", id);
        deleteCustomer.execute(id);
        log.info("Customer with id {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAll() {
        log.info("Get all request received for customers");
        List<Customer> customers = findAllCustomers.execute();
        log.info("All customers retrieved successfully, total count: {}", customers.size());
        return ResponseEntity.ok(customers);
    }
}
