package com.anderson.hotel_reservation_system.entrypoint.employee.controller;

import com.anderson.hotel_reservation_system.core.employee.domain.Employee;
import com.anderson.hotel_reservation_system.core.employee.dtos.EmployeeDTO;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.DeleteEmployeeUseCasePort;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.FindEmployeeByIdUseCasePort;
import com.anderson.hotel_reservation_system.core.employee.usecases.ports.RegisterEmployeeUseCasePort;
import com.anderson.hotel_reservation_system.entrypoint.employee.dtos.EmployeeRequestDTO;
import com.anderson.hotel_reservation_system.entrypoint.employee.dtos.EmployeeResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

import static com.anderson.hotel_reservation_system.entrypoint.employee.mapper.EmployeeControllerDTOMapper.toEmployeeDTO;
import static com.anderson.hotel_reservation_system.entrypoint.employee.mapper.EmployeeControllerDTOMapper.toEmployeeResponseDTO;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private RegisterEmployeeUseCasePort registerEmployee;

    @Autowired
    private FindEmployeeByIdUseCasePort findEmployeeById;

    @Autowired
    private DeleteEmployeeUseCasePort deleteEmployee;


    @PostMapping("/register")
    public ResponseEntity<EmployeeResponseDTO> register(@Valid @RequestBody EmployeeRequestDTO dto) {
        EmployeeDTO employeeDTO = toEmployeeDTO(dto);
        Employee employee = registerEmployee.execute(employeeDTO);
        EmployeeResponseDTO employeeResponseDTO = toEmployeeResponseDTO(employee);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employeeResponseDTO.id())
                .toUri())
                .body(employeeResponseDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeResponseDTO> get(@PathVariable("id") UUID id) {
        Employee employee = findEmployeeById.execute(id);
        EmployeeResponseDTO dto = toEmployeeResponseDTO(employee);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteEmployee.execute(id);
        return ResponseEntity.noContent().build();
    }
}
